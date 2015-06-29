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

import org.apache.log4j.Logger;
import com.tilab.ca.platform.logintegration.CC;
import com.tilab.ca.platform.logintegration.util.Constants;
import javax.ws.rs.core.MultivaluedHashMap;

public class SSEUtils {

    static Logger LOG = Logger.getLogger(SSEUtils.class.getName());

    public static void optional(Behaviour b, String warning) {
        try {
            b.behaviour();
        } catch (Exception e) {
            LOG.warn("Warn = " + e.getMessage());
        }
    }

    public static <T> T optional(Ret<T> ret, T defaultValue) {
        T returnValue = null;
        try {
            returnValue = ret.ret();
        } catch (Exception e) {
            LOG.warn("Warn = " + e.getMessage());
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
            LOG.error("Error = " + e.getMessage());
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
            LOG.error("Error = " + e.getMessage());
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

    public static MultivaluedHashMap withLogServerHeaders() {

        MultivaluedHashMap<String, Object> headers = new MultivaluedHashMap<>();
        if (CC.getServerRequestID() != null) {
            headers.add(Constants.SERVER_REQUEST_ID, CC.getServerRequestID());
        }
        if (CC.getClientRequestID() != null) {
            headers.add(Constants.CLIENT_REQUEST_ID, CC.getClientRequestID());
        }
        if (CC.getUser() != null) {
            headers.add(Constants.USER, CC.getUser());
        }
        if (CC.getApp() != null) {
            headers.add(Constants.APP, CC.getApp());
        }
        return headers;
    }
}
