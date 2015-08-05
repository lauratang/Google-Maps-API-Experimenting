public class CityObj {

	private String name;
	private String lat;
	private String lng;
	
	public CityObj(String name, String lat, String lng) {
		this.name = name;
		this.lat = lat;
		this.lng = lng;
	}
	
	// getMethods
	public String getLat() { return lat; }
	public String getLng() { return lng; }
	public String getName() { return name; }
	
	// setMethods
	public void setLat(String lat) { this.lat = lat; }
	public void setLng(String lng) { this.lng = lng; }
	public void setName(String name) { this.name = name; }
	
}
