package book.model;

public class Root {

	ParamData paramData;
	Result result;
	
	
	public ParamData getParamData() {
		return paramData;
	}
	public void setParamData(ParamData paramData) {
		this.paramData = paramData;
	}
	public Result getResult() {
		return result;
	}
	public void setResult(Result result) {
		this.result = result;
	}
	@Override
	public String toString() {
		return "Root [paramData=" + paramData + ", result=" + result + "]";
	}
	
	
	
	
}
