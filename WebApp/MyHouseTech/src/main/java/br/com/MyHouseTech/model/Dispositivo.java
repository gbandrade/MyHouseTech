package br.com.MyHouseTech.model;

public class Dispositivo {
	//============================
	// 	Declarations
	//============================
		
	private String midDispositivo;
	private String midArduino;
	private String midUsuario;
	private String mstrIp;
	private Integer	mintTempMin;
	private Integer	mintTempMax;
	
	
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
	
	public Integer getTempMin() {
		return mintTempMin;
	}
	
	public void setTempMin(Integer intTempMin) {
		this.mintTempMin = intTempMin;
	}
	
	public Integer getTempMax() {
		return mintTempMax;
	}
	
	public void setTempMax(Integer intTempMax) {
		this.mintTempMax = intTempMax;
	}
	
	
	//============================
	// Constructor
	//============================

	public Dispositivo() {
		this.mintTempMin = null;
		this.mintTempMax = null;
	}
	
	public Dispositivo(String strIdDispositivo, String strIdArduino, String strIdUsuario, String strIp) {
		this.midDispositivo = strIdDispositivo;
		this.midArduino = strIdArduino;
		this.midUsuario = strIdUsuario;
		this.mstrIp = strIp;
	}
	
	public Dispositivo(String strIdDispositivo, String strIdArduino, String strIdUsuario, String strIp, int intTempMin, int intTempMax) {
		this.midDispositivo = strIdDispositivo;
		this.midArduino = strIdArduino;
		this.midUsuario = strIdUsuario;
		this.mstrIp = strIp;
		this.mintTempMin = intTempMin;
		this.mintTempMax = intTempMax;
	}
}
