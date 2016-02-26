package br.fatec.sigapf.framework.utils;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service(value = "systemUtils")
public class SystemUtils {

	private ApplicationContext applicationContext;

	public String pegarTextoArquivo(String url) {
		Resource resource = applicationContext.getResource(url);
		InputStream is = null;
		try {
			is = resource.getInputStream();
		} catch (IOException el) {
			el.printStackTrace();
		}
		BufferedInputStream bis = new BufferedInputStream(is);
		ByteArrayOutputStream buf = new ByteArrayOutputStream();
		int result;
		try {
			result = bis.read();
			while (result != -1) {
				byte b = (byte) result;
				buf.write(b);
				result = bis.read();
			}
			return buf.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

}