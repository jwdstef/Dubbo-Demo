package cn.finance.dubbo.framework.result;

/**
 * 
 * JSON模型
 * 
 * 用户后台向前台返回的JSON对象
 * 
 * @author liuyijun
 * 
 */
public class Json implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5116979826265269142L;

	private boolean success = false;

	private String msg = "";

	private Object obj = null;
	/**
	 * 操作类型 edit 或 add
	 */
	private String action;

	public boolean getSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Json(boolean success) {
		super();
		this.success = success;
	}

	public Json(boolean success, String msg) {
		super();
		this.success = success;
		this.msg = msg;
	}

	public Json(boolean success, String msg, Object obj) {
		super();
		this.success = success;
		this.msg = msg;
		this.obj = obj;
	}

	public Json(boolean success, Object obj) {
		super();
		this.success = success;
		this.obj = obj;
	}
	
	
	public Json() {
		super();
	}

	public static Json resultFalseAndMsg(String msg){
		return new Json(false, msg);
	}
	
	public static Json resultTrueAndMsg(String msg){
		return new Json(true, msg);
	}
	
	public static Json resultTrueAndObj(Object obj){
		return new Json(true, obj);
	}
	
	public static Json resultFalseAndObj(Object obj){
		return new Json(false, obj);
	}
	
	public static Json resultTrue(){
		return new Json(true);
	}

	public static Json resultFalse(){
		return new Json(false);
	}
	
	public Json redirectPage(String url){
		this.setAction(url);
		return this;
	}
	
	
}
