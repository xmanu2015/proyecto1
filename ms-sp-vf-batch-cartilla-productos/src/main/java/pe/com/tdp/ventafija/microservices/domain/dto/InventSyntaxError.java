package pe.com.tdp.ventafija.microservices.domain.dto;

public class InventSyntaxError {
	public static final String CAUSE_INCOMPLETE_LINE = "INCOMPLETE_LINE";
	
	private String cause;
	private String line;

	public InventSyntaxError(String cause, String line) {
		super();
		this.cause = cause;
		this.line = line;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

}
