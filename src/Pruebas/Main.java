package Pruebas;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParserFactory;

import org.w3c.dom.Text;

public class Main {

	private static final int REQUEST_CODE = 1234;
	private static String palabra = "";
	private static Thread thread;

	public static String cargarMetodo(String URL) throws IOException {
		InputStream in = null;
		try
		{
			URL xbmcURL = new URL (URL);
			HttpURLConnection urlConnection = (HttpURLConnection) xbmcURL.openConnection();
			urlConnection.setReadTimeout(10000 /* milliseconds */);
			urlConnection.setConnectTimeout(15000 /* milliseconds */);
			urlConnection.setRequestMethod("GET");
			urlConnection.setDoInput(true);
			// Starts the query
			urlConnection.connect();
			int response = urlConnection.getResponseCode();
			in = urlConnection.getInputStream();

			// Convert the InputStream into a string
			String contentAsString = readIt(in, 500);
			return contentAsString;
		}
		finally {
			if (in != null) {
				in.close();
			}
		}
	}

	public static String readIt(InputStream stream, int len) throws IOException, UnsupportedEncodingException {
		Reader reader = null;
		reader = new InputStreamReader(stream, "UTF-8");
		char[] buffer = new char[len];
		reader.read(buffer);
		return new String(buffer);
	}

	public static void ejecution() {
		thread = new Thread(new Runnable()
		{
			@Override
			public void run(){
				String chequeo = "";
				InputStream in = null;
				try 
				{
					URL xbmcURL = new URL ("http://192.168.0.102/jsonrpc?request={%22jsonrpc%22:%20%222.0%22,%20%22id%22:%201,%20%22method%22:%20%22Input.Up%22}");
					HttpURLConnection urlConnection = (HttpURLConnection) xbmcURL.openConnection();
					//					chequeo.setText(urlConnection.getResponseMessage());
					in = new BufferedInputStream(urlConnection.getInputStream());
					System.out.println("Codigo HTTP: "+in.read());	
					//					chequeo.setText("Esto fue cheque: "+ palabra);
					urlConnection.disconnect();
					in.close();		
					System.out.println("Ejecuto");
				} 
				catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("Error: "+e.getMessage()+" Local: "+e.getLocalizedMessage());
				}
			}
		});
		thread.start();		  
	}
	public static void procesarMetodo(String metodo) throws IOException
	{
		String ipXmbc ="192.168.0.102";
		String met = "";
		String[] palabras = metodo.split(" ");
		if (metodo.equals("arriba")) {
			met = "http://"+ipXmbc+"/jsonrpc?request={%22jsonrpc%22:%20%222.0%22,%20%22id%22:%201,%20%22method%22:%20%22Input.Up%22}";
			//			ejecutarURL(met, null, "Ejecuto Arriba");
			cargarMetodo(met);
			System.out.println("Ejecuto_Arriba");


		} else if (metodo.equals("abajo")) {
			met = "http://"+ipXmbc+"/jsonrpc?request={%22jsonrpc%22:%20%222.0%22,%20%22id%22:%201,%20%22method%22:%20%22Input.Down%22}";
			cargarMetodo(met);
			System.out.println("Ejecuto Abajo");

		} else if (metodo.equals("izquierda")) {
			met = "http://"+ipXmbc+"/jsonrpc?request={%22jsonrpc%22:%20%222.0%22,%20%22id%22:%201,%20%22method%22:%20%22Input.Left%22}";
			cargarMetodo(met);
			System.out.println("Ejecuto izquierda");

		} else if (metodo.equals("derecha")) {
			met = "http://"+ipXmbc+"/jsonrpc?request={%22jsonrpc%22:%20%222.0%22,%20%22id%22:%201,%20%22method%22:%20%22Input.Right%22}";
			cargarMetodo(met);
			System.out.println("Ejecuto derecha");

		} else if (metodo.equals("aceptar")) {
			met = "http://"+ipXmbc+"/jsonrpc?request={%22jsonrpc%22:%20%222.0%22,%20%22id%22:%201,%20%22method%22:%20%22Input.Select%22}";
			cargarMetodo(met);
			System.out.println("Ejecuto aceptar");
		}

		else if (metodo.equals("home") || metodo.equals("inicio") || metodo.equals("salir")) {
			met = "http://"+ipXmbc+"/jsonrpc?request={%22jsonrpc%22:%20%222.0%22,%20%22id%22:%201,%20%22method%22:%20%22Input.Home%22}";
			cargarMetodo(met);
			System.out.println("Ejecuto Home");
		}
		else if (metodo.equalsIgnoreCase("regresar") || metodo.equalsIgnoreCase("volver") || metodo.equalsIgnoreCase("atrás")) {
			met = "http://"+ipXmbc+"/jsonrpc?request={%22jsonrpc%22:%20%222.0%22,%20%22id%22:%201,%20%22method%22:%20%22Input.Back%22}";
			cargarMetodo(met);
			System.out.println("Ejecuto regresar");
		}
		else if (metodo.equalsIgnoreCase("imágenes")) {
			met = "http://"+ipXmbc+"/jsonrpc?request={%20%22jsonrpc%22:%20%222.0%22,%20%22method%22:%20%22Input.ExecuteAction%22,%20%22params%22:%20{%20%22action%22:%20%22pageup%22%20},%20%22id%22:%201%20}";
			cargarMetodo(met);
			System.out.println("Usted dijo: Imágenes");
			//met = "http://"+ipXmbc+"/jsonrpc?request={%22jsonrpc%22:%20%222.0%22,%20%22id%22:%201,%20%22method%22:%20%22Input.Select%22}";
			//ejecutarURL(met, consola, "Ejecuto aceptar");
		}
		else if (metodo.equalsIgnoreCase("música")) {
			met = "http://"+ipXmbc+"/jsonrpc?request={%20%22jsonrpc%22:%20%222.0%22,%20%22method%22:%20%22Input.ExecuteAction%22,%20%22params%22:%20{%20%22action%22:%20%22pageup%22%20},%20%22id%22:%201%20}";
			cargarMetodo(met);
			met = "http://"+ipXmbc+"/jsonrpc?request={%22jsonrpc%22:%20%222.0%22,%20%22id%22:%201,%20%22method%22:%20%22Input.Right%22}";
			cargarMetodo(met);
			met = "http://"+ipXmbc+"/jsonrpc?request={%22jsonrpc%22:%20%222.0%22,%20%22id%22:%201,%20%22method%22:%20%22Input.Right%22}";
			cargarMetodo(met);
			System.out.println("Seleccionó Música");
			//met = "http://"+ipXmbc+"/jsonrpc?request={%22jsonrpc%22:%20%222.0%22,%20%22id%22:%201,%20%22method%22:%20%22Input.Select%22}";
			//ejecutarURL(met, consola, "Ejecuto aceptar");
		}
		else if (metodo.equalsIgnoreCase("videos") || metodo.equalsIgnoreCase("nueva búsqueda")) {
			met = "http://"+ipXmbc+"/jsonrpc?request={%20%22jsonrpc%22:%20%222.0%22,%20%22method%22:%20%22Input.ExecuteAction%22,%20%22params%22:%20{%20%22action%22:%20%22pageup%22%20},%20%22id%22:%201%20}";
			cargarMetodo(met);
			met = "http://"+ipXmbc+"/jsonrpc?request={%22jsonrpc%22:%20%222.0%22,%20%22id%22:%201,%20%22method%22:%20%22Input.Right%22}";
			cargarMetodo(met);
			System.out.println("Seleccionó Videos");
			//met = "http://"+ipXmbc+"/jsonrpc?request={%22jsonrpc%22:%20%222.0%22,%20%22id%22:%201,%20%22method%22:%20%22Input.Select%22}";
			//ejecutarURL(met, consola, "Ejecuto aceptar");
		}

		else if (metodo.equalsIgnoreCase("programas") || metodo.equalsIgnoreCase("búsqueda")) {
			met = "http://"+ipXmbc+"/jsonrpc?request={%20%22jsonrpc%22:%20%222.0%22,%20%22method%22:%20%22Input.ExecuteAction%22,%20%22params%22:%20{%20%22action%22:%20%22pageup%22%20},%20%22id%22:%201%20}";
			cargarMetodo(met);
			met = "http://"+ipXmbc+"/jsonrpc?request={%22jsonrpc%22:%20%222.0%22,%20%22id%22:%201,%20%22method%22:%20%22Input.Right%22}";
			cargarMetodo(met);
			met = "http://"+ipXmbc+"/jsonrpc?request={%22jsonrpc%22:%20%222.0%22,%20%22id%22:%201,%20%22method%22:%20%22Input.Right%22}";
			cargarMetodo(met);
			met = "http://"+ipXmbc+"/jsonrpc?request={%22jsonrpc%22:%20%222.0%22,%20%22id%22:%201,%20%22method%22:%20%22Input.Right%22}";
			cargarMetodo(met);
			System.out.println("Usted dijo: programas");
			//met = "http://"+ipXmbc+"/jsonrpc?request={%22jsonrpc%22:%20%222.0%22,%20%22id%22:%201,%20%22method%22:%20%22Input.Select%22}";
			//ejecutarURL(met, consola, "Ejecuto aceptar");
		}
		else if (metodo.equalsIgnoreCase("sistema")) {
			met = "http://"+ipXmbc+"/jsonrpc?request={%20%22jsonrpc%22:%20%222.0%22,%20%22method%22:%20%22Input.ExecuteAction%22,%20%22params%22:%20{%20%22action%22:%20%22pagedown%22%20},%20%22id%22:%201%20}";
			cargarMetodo(met);
			//met = "http://"+ipXmbc+"/jsonrpc?request={%22jsonrpc%22:%20%222.0%22,%20%22id%22:%201,%20%22method%22:%20%22Input.Select%22}";
			//ejecutarURL(met, consola, "Ejecuto aceptar");
		}
		else if (metodo.equalsIgnoreCase("youtube")) {
			met = "http://"+ipXmbc+"/jsonrpc?request={%22jsonrpc%22:%20%222.0%22,%20%22id%22:%201,%20%22method%22:%20%22Input.Down%22}";
			cargarMetodo(met);
			met = "http://"+ipXmbc+"/jsonrpc?request={%22jsonrpc%22:%20%222.0%22,%20%22id%22:%201,%20%22method%22:%20%22Input.Down%22}";
			cargarMetodo(met);
			System.out.println("Accediendo a Youtube");
			met = "http://"+ipXmbc+"/jsonrpc?request={%20%22jsonrpc%22:%20%222.0%22,%20%22method%22:%20%22Input.ExecuteAction%22,%20%22params%22:%20{%20%22action%22:%20%22pagedown%22%20},%20%22id%22:%201%20}";
			cargarMetodo(met);
			//met = "http://"+ipXmbc+"/jsonrpc?request={%22jsonrpc%22:%20%222.0%22,%20%22id%22:%201,%20%22method%22:%20%22Input.Select%22}";
			//ejecutarURL(met, consola, "Ejecuto aceptar");
		}

		else if (metodo.equalsIgnoreCase("pausar") || metodo.equalsIgnoreCase("pausa") || metodo.equalsIgnoreCase("reproducir")) {
			met = "http://"+ipXmbc+"/jsonrpc?request={%22jsonrpc%22:%222.0%22,%22method%22:%22Player.PlayPause%22,%22params%22:{%22playerid%22:%201%20},%22id%22:1}";
			cargarMetodo(met);
		}

		else if (metodo.equalsIgnoreCase("detener") || metodo.equalsIgnoreCase("parar" +
				"")|| metodo.equalsIgnoreCase("stop") || metodo.equalsIgnoreCase("sop") ) {
			met = "http://"+ipXmbc+"/jsonrpc?request={%22jsonrpc%22:%222.0%22,%22method%22:%22Player.stop%22,%22params%22:{%22playerid%22:%201%20},%22id%22:1}";
			cargarMetodo(met);
			System.out.println("Audio o Video detenido");
		}

		else if (palabras[0].equals("buscar")) {

			String busqueda = "";
			for(int i = 1; i < palabras.length; i++)
			{
				if( i == palabras.length-1 )
					busqueda += palabras[i];

				else
					busqueda += busqueda + palabras[i]+"%20";

			}
			busqueda = "%22" + busqueda +"%22";
			met = "http://"+ipXmbc+"/jsonrpc?request={%22jsonrpc%22:%222.0%22,%22id%22:%220%22,%22method%22:%22Input.SendText%22,%22params%22:{%22text%22:"+busqueda+",%22done%22:true}}";

			cargarMetodo(met);
			System.out.println("Buscando canción" );


		}
		else
		{
			try {
				cargarMetodo(met);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			System.out.println("Comando no reconocido");
		}
	}

	public static void main(String[] args) throws IOException {
		//		ejecution();
		procesarMetodo("abajo");

	}

}

