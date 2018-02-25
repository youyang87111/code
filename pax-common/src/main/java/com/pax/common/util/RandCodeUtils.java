package com.pax.common.util;

import java.util.Random;
import java.util.UUID;

/**
 * 
 * @Filename RandCodeUtils.java
 *
 * @Description
 *
 * @Version 1.0
 *
 * @Author
 *
 * @Email
 * 
 * @History <li>Author:</li> <li>Date: Nov 25, 2014</li> <li>Version: 1.0</li>
 *          <li>Content: create</li>
 *
 */
public class RandCodeUtils {

	public static String getRandCode(int length) {

		StringBuffer sRand = new StringBuffer("");

		Random random = new Random();

		for (int i = 0; i < length; i++) {

			int r = random.nextInt(62);
			String rand;
			if (r < 10) {// 数字
				rand = String.valueOf(r);
			} else if (r > 9 && r < 36) {// 大写字母
				char temp = (char) (r + 55);
				rand = String.valueOf(temp);
			} else {// 小写字母
				char temp = (char) (r + 61);
				rand = String.valueOf(temp);
			}

			sRand.append(rand);

		}

		return sRand.toString();
	}

	public static void main(String[] args) {
		System.out.println(RandCodeUtils.getRandCode(8));
	}

	public static String getUUID() {
		UUID uuid = UUID.randomUUID();
		String str = uuid.toString();
		// 去掉"-"符号
		String temp = str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23) + str.substring(24);
		return temp;
	}
}
