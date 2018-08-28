/**
 * 
 */
package com.itests.fx.common;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.commons.beanutils.BeanUtils;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author jatinma
 * 
 *         This is the generic class written for converting any format of the
 *         JSON data to to the Java Object without making any wrapper classes.
 *         This will increase the functionality to map the required Dto object
 *         from the JSON.
 *
 */
public class JsonToJavaObjectConverter implements Serializable {

	/**
	 * JsonToJavaObjectConverter is the class wrapper for the fx-itests
	 */

	private static final long serialVersionUID = 6544187620932844926L;

	private static Map<Class<?>, Map<String, Field>> classJSONFieldMap = new HashMap<>();

	public static CopyOnWriteArrayList<Object> processData(String classPath, String jsonResponse, String className)
			throws Exception {
		ObjectMapper mapper = new ObjectMapper();

		if (jsonResponse == null || classPath == null) {
			return null;
		}

		JsonNode root = null;
		try {
			root = mapper.readTree(jsonResponse).findParent(className);
			if (root == null) {
				root = mapper.readTree(jsonResponse);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return processDataElementBase(root, classPath, null, className);
	}

	/**
	 * 
	 * 
	 * @param root
	 *            :JsonNode
	 * @param classPath
	 *            :String
	 * @param existingObject
	 *            :Object
	 * @param className
	 *            :String
	 * @return
	 * @throws Exception
	 */
	private static CopyOnWriteArrayList<Object> processDataElementBase(JsonNode root, String classPath,
			Object existingObject, String className) throws Exception {
		CopyOnWriteArrayList<Object> list = new CopyOnWriteArrayList<Object>();
		if (root.isArray()) {
			if (existingObject != null) {
				list = new CopyOnWriteArrayList<Object>();
			} else {
				list = new CopyOnWriteArrayList<>();
			}
			for (JsonNode arrayNode : root) {
				Object obj = processDataElement(classPath, arrayNode, existingObject);
				list.add(obj);
			}
		} else {
			JsonNode arrayNode = root.get(className);
			Object obj = processDataElement(classPath, arrayNode, existingObject);
			list.add(obj);
		}
		return list;
	}

	/**
	 * 
	 * 
	 * @param className
	 *            :String
	 * @param root
	 *            :JsonNode
	 * @param data
	 *            :Object
	 * @return
	 * @throws Exception
	 */
	private static Object processDataElement(String className, JsonNode root, Object data) throws Exception {
		Object target = data;
		if (className != null) {
			target = Class.forName(className).newInstance();
		}
		Map<String, Field> jsonFieldMap = getJSONFieldMap(target.getClass());
		Iterator<Entry<String, JsonNode>> jiterator = root.fields();
		while (jiterator.hasNext()) {
			Entry<String, JsonNode> entry = jiterator.next();

			String fieldName = entry.getKey();
			Field field = null;

			if (field == null) {
				field = jsonFieldMap.get(fieldName);
			}
			if (field == null) {
				continue;
			}

			JsonNode value = entry.getValue().get("AccountInternalId");
			if (value == null) {
				value = entry.getValue();
			}
			if (value != null) {
				Object setValue;
				if (value.isTextual()) {
					setValue = value.textValue();
				} else {
					setValue = value;
				}

				BeanUtils.setProperty(target, field.getName(), setValue);
			} else if (entry.getValue().isArray()) {
				ParameterizedType type = (ParameterizedType) field.getGenericType();
				Class<?> listClass = (Class<?>) type.getActualTypeArguments()[0];
				List<Object> list = new ArrayList<>();
				String listClassName = listClass.getName();
				for (JsonNode arrayNode : entry.getValue()) {
					Object obj = processDataElement(listClassName, arrayNode, null);
					list.add(obj);
				}
				if (!list.isEmpty()) {
					BeanUtils.setProperty(target, field.getName(), list);
				}
			}
		}

		return target;
	}

	/*
	 * Only process the class once to get the JsonProperty annotations and
	 * fields
	 */
	private static synchronized Map<String, Field> getJSONFieldMap(Class<?> clazz) {

		Map<String, Field> jsonFieldMap = classJSONFieldMap.get(clazz);

		if (jsonFieldMap == null) {
			jsonFieldMap = new HashMap<>();
			for (Field field : clazz.getDeclaredFields()) {
				JsonProperty jsonProperty = field.getAnnotation(JsonProperty.class);
				if (jsonProperty != null) {
					jsonFieldMap.put(jsonProperty.value(), field);
				}
			}

			if (!jsonFieldMap.isEmpty()) {
				classJSONFieldMap.put(clazz, jsonFieldMap);
			}
		}
		return jsonFieldMap;
	}

}
