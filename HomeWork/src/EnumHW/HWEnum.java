package EnumHW;

public class HWEnum {

	public enum PlanetEnum {
		수성(2439), 금성(6052), 지구(6371), 화성(3390), 목성(69911), 토성(58232), 천왕성(25362), 해왕성(24622);

		private double radius;

		PlanetEnum(double radius) {
			this.radius = radius;
		}

		public double getRadius() {
			return radius;
		}

	}

	public static void main(String[] args) {
		
		for (PlanetEnum planet : PlanetEnum.values()) {
			
			System.out.println(planet + "의 반지름은 " + planet.getRadius() + "km이고, 면적은 "
					+ (4 * planet.getRadius() * planet.getRadius() * 3.141592) + "km²입니다.");
		
		}
	}
}
