/*-
 * Copyright (C) 2014 Riccardo MuzzÃ¬.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.tilab.ca.ssefrontend.util;

import java.util.logging.Level;
import java.util.logging.Logger;

public class SSEUtils {

	static Logger LOG = Logger.getLogger(SSEUtils.class.getName()); 

	public static void optional(Behaviour b, String warning) {
		try {
			b.behaviour();
		} catch (Exception e) {
			LOG.log(Level.WARNING, "Warn = {0}", e.getMessage());
		}
	}

	public static <T> T optional(Ret<T> ret, T defaultValue) {
		T returnValue = null;
		try {
			returnValue = ret.ret();
		} catch (Exception e) {
			LOG.log(Level.WARNING, "Warn = {0}", e.getMessage());
		}
		if (returnValue == null) {
			returnValue = defaultValue;
		}
		return returnValue;
	}

	public static <T> T unchecked(Ret<T> ret, T defaultValue) {
		T returnValue = null;
		try {
			returnValue = ret.ret();
		} catch (Exception e) {
                        LOG.log(Level.SEVERE, "Error = {0}", e.getMessage());
			throw new RuntimeException(e);
		}
		if (returnValue == null) {
			returnValue = defaultValue;
		}
		return returnValue;
	}

	public static <T> T optional(Ret<T> ret) {
		return optional(ret, null);
	}

	public static <T> T unchecked(Ret<T> ret) {
		return unchecked(ret, null);
	}

	public static void unchecked(Behaviour ret) {
		try {
			ret.behaviour();
		} catch (Exception e) {
                        LOG.log(Level.SEVERE, "Error = {0}", e.getMessage());
			throw new RuntimeException(e);
		}
	}

	public static <T> T uncheck(Ret<T> ret, T defaultValue) {
		return unchecked(ret, defaultValue);
	}

	public static String getFileExtension(String fileName) {
		String[] splat = fileName.split(".");
		return splat[splat.length - 1];
	}

	public static boolean hasContent(String string) {
		return string != null && !string.isEmpty();
	}

	public static boolean hasNoContent(String string) {
		return !hasContent(string);
	}
}
