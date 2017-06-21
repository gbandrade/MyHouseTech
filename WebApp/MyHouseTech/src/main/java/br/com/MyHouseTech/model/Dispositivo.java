package br.com.MyHouseTech.model;

public class Dispositivo {
	//============================
	// 	Declarations
	//============================
		
	private String midDispositivo;
	private String midArduino;
	private String midUsuario;
	private String mstrIp;
	
	
	//============================
	// Get's & Set's
	//============================	
	
	public String getIdDispositivo() {
		return midDispositivo;
	}
	public void setIdDispositivo(String idDispositivo) {
		this.midDispositivo = idDispositivo;
	}
	public String getIdArduino() {
		return midArduino;
	}
	public void setIdArduino(String idArduino) {
		this.midArduino = idArduino;
	}
	public String getIdUsuario() {
		return midUsuario;
	}
	public void setIdUsuario(String idUsuario) {
		this.midUsuario = idUsuario;
	}
	public String getIp() {
		return mstrIp;
	}
	public void setIp(String strIp) {
		this.mstrIp = strIp;
	}
	
	//============================
	// Constructor
	//============================
	
	public Dispositivo() {}
	
	public Dispositivo(String strIdDispositivo, String strIdArduino, String strIdUsuario, String strIp) {
		this.midDispositivo = strIdDispositivo;
		this.midArduino = strIdArduino;
		this.midUsuario = strIdUsuario;
		this.mstrIp = strIp;
	}
}
