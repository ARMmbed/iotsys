/*******************************************************************************
 * Copyright (c) 2013
 * Institute of Computer Aided Automation, Automation Systems Group, TU Wien.
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 * 3. Neither the name of the Institute nor the names of its contributors
 *    may be used to endorse or promote products derived from this software
 *    without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE INSTITUTE AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED.  IN NO EVENT SHALL THE INSTITUTE OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS
 * OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
 * OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * 
 * This file is part of the IoTSyS project.
 ******************************************************************************/

package at.ac.tuwien.auto.iotsys.commons.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public abstract class UriEncoder {
	public static String getEscapedUri(String decodedStr) {
		String tmp = decodedStr;

		// lower case
		tmp = tmp.toLowerCase();

		// replace characters
		tmp = tmp.replace(" ", "_");
		tmp = tmp.replace("-", "_");
		tmp = tmp.replace("/", "_");
		tmp = tmp.replace("ß", "ss");
		tmp = tmp.replace("ä", "ae");
		tmp = tmp.replace("ö", "oe");
		tmp = tmp.replace("ü", "ue");
		tmp = tmp.replace("²", "2");
		tmp = tmp.replace("³", "3");
		tmp = tmp.replace("!", "");
		tmp = tmp.replace("?", "");
		tmp = tmp.replace("=", "");
		tmp = tmp.replace("&", "and");
		tmp = tmp.replace("$", "");
		tmp = tmp.replace(",", "");
		tmp = tmp.replace(".", "");
		tmp = tmp.replace("(", "");
		tmp = tmp.replace(")", "");
		tmp = tmp.replace("[", "");
		tmp = tmp.replace("]", "");
		tmp = tmp.replace("{", "");
		tmp = tmp.replace("}", "");

		try {
			tmp = URLEncoder.encode(tmp, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return tmp;
	}
}
