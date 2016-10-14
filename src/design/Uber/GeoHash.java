package design.Uber;

public class GeoHash {
	public String encode(double latitude, double longitude, int precision) {
		// Base32:0-9, a-z 去掉 (a,i,l,o)
		String _base32 = "0123456789bcdefghjkmnpqrstuvwxyz";
		// 维度
		String lat_bin = getBin(latitude, -90, 90);
		// 经度
		String lng_bin = getBin(longitude, -180, 180);
		System.out.println(lat_bin);
		System.out.println(lng_bin);

		StringBuffer hash_code = new StringBuffer();
		StringBuffer b = new StringBuffer();
		for (int i = 0; i < 30; ++i) {
			// 先经度， 后维度 经纬度交替
			b.append(lng_bin.charAt(i));
			b.append(lat_bin.charAt(i));
		}
		// 001100111001101110011000000110010001111000100110010101000000, 
		// 每5位组合成一个字符
		for (int i = 0; i < 60; i += 5) {
			int index = binary2Int(b.substring(i, i + 5));
			hash_code.append(_base32.charAt(index));
		}
		return hash_code.substring(0, precision);
	}

	// input String 是 1010,类似的二进制表示方法，
	public int binary2Int(String bin) {
		return Integer.parseInt(bin, 2);
	}

	// 二分查找, PPT page 23, 将经纬度 转换了1, 0代表的字符串
	public String getBin(double value, double left, double right) {
		StringBuffer b = new StringBuffer();
		for (int i = 0; i < 30; ++i) {
			double mid = (left + right) / 2.0;
			if (value > mid) {
				left = mid;
				b.append("1");
			} else {
				right = mid;
				b.append("0");
			}
		}
		return b.toString();
	}
	
	public static void main(String [] args){
		GeoHash test = new GeoHash();
		System.out.println(test.encode(-30.043800, -51.140220,12));
	}
}
