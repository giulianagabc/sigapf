package br.fatec.sigapf.framework.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.hibernate.collection.internal.PersistentSet;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;

import br.fatec.sigapf.framework.exception.SystemRuntimeException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@FacesConverter(value = "jsonConverter")
public class JsonConverter implements Converter {

	private Gson gson;

	public JsonConverter() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.disableInnerClassSerialization().setExclusionStrategies(
				new SystemGsonExclusionStrategy(PersistentSet.class));
		gsonBuilder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		gson = gsonBuilder.create();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String string) {
		try {
			if (string == null || string.trim().equals("")) {
				return null;
			}
			if (string.contains("\'")) {
				string = string.replace("\'", "\"");
			}
			JSONObject jsonObject = new JSONObject(string);
			Class targetClass = Class.forName(jsonObject.getString("class"));
			return gson.fromJson(string, targetClass);
		} catch (ClassNotFoundException | JSONException e) {
			throw new SystemRuntimeException(
					"Erro ao converter string para objeto: " + e.getMessage());
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object object) {
		try {
			if (object != null) {
				JSONObject jsonObject = new JSONObject(gson.toJson(object));
				jsonObject.put("class", object.getClass().getName());
				return jsonObject.toString().replaceAll("\"", "\'");
			} else {
				return "";
			}
		} catch (JSONException e) {
			throw new SystemRuntimeException(
					"Erro ao converter objeto para string: " + e.getMessage());
		}
	}

}
