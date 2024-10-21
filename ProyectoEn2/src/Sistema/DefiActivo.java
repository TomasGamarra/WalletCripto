package Sistema;

public class DefiActivo {
private float amount;
private float rendiminetoEnUsd;
private Protocolo protocolo;


public DefiActivo(float amount, float rendiminetoEnUsd, Protocolo protocolo) {
	this.amount = amount;
	this.rendiminetoEnUsd = rendiminetoEnUsd;
	this.protocolo = protocolo;
}


public float getAmount() {
	return amount;
}


public void setAmount(float amount) {
	this.amount = amount;
}


public float getRendiminetoEnUsd() {
	return rendiminetoEnUsd;
}


public void setRendiminetoEnUsd(float rendiminetoEnUsd) {
	this.rendiminetoEnUsd = rendiminetoEnUsd;
}


public Protocolo getProtocolo() {
	return protocolo;
}


public void setProtocolo(Protocolo protocolo) {
	this.protocolo = protocolo;
}
	
}
