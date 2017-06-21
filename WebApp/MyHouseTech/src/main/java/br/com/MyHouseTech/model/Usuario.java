package br.com.MyHouseTech.model;

public class Usuario 
{
	//============================
	// 	Declarations
	//============================
	
	private String	midUsuario;
	private String	mnome;
	private String	memail;
	private String	mlogin;
	private String	msenha;
	private	String	mcpf;
	private	String	mtelefone;
	private	String	mendereco;
	private	String	midPerfil;
	
	//============================
	// Get's & Set's
	//============================	
	
	public String getIdUsuario() {
		return midUsuario;
	}
	public void setIdUsuario(String idUsuario) {
		this.midUsuario = idUsuario;
	}
	public String getNome() {
		return mnome;
	}
	public void setNome(String nome) {
		this.mnome = nome;
	}
	public String getEmail() {
		return memail;
	}
	public void setEmail(String email) {
		this.memail = email;
	}
	public String getLogin() {
		return mlogin;
	}
	public void setLogin(String login) {
		this.mlogin = login;
	}
	public String getSenha() {
		return msenha;
	}
	public void setSenha(String senha) {
		this.msenha = senha;
	}
	public String getCpf() {
		return mcpf;
	}
	public void setCpf(String cpf) {
		this.mcpf = cpf;
	}
	public String getTelefone() {
		return mtelefone;
	}
	public void setTelefone(String telefone) {
		this.mtelefone = telefone;
	}
	public String getEndereco() {
		return mendereco;
	}
	public void setEndereco(String endereco) {
		this.mendereco = endereco;
	}
	public String getIdPerfil() {
		return midPerfil;
	}
	public void setIdPerfil(String idPerfil) {
		this.midPerfil = idPerfil;
	}
	
	//============================
	// Constructor
	//============================	
	
	public Usuario(){}
	
	//----------------------------
	
	public Usuario(	String idUsuario, String nome, String email, String login, String senha, 
					String	cpf, String	telefone, String endereco, String idPerfil) {
		this.midUsuario = idUsuario;
		this.mnome = nome;
		this.memail = email;
		this.mlogin = login;
		this.msenha = senha;
		this.mcpf = cpf;
		this.mtelefone = telefone;
		this.mendereco = endereco;
		this.midPerfil = idPerfil;
		
	}
	
}
