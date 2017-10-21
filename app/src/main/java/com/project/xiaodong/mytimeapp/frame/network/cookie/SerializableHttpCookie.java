/**
 * @file SerializableHttpCookie
 * @copyright (c) 2016 Macalline All Rights Reserved.
 * @author SongZheng
 * @date 2016/5/11
 */
package com.project.xiaodong.mytimeapp.frame.network.cookie;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.HttpCookie;

/**
 * @author SongZheng
 * @description TODO
 * @date 2016/5/11
 */
public class SerializableHttpCookie implements Serializable {
    /*******************************************************************************
     *	Public/Protected Variables
     *******************************************************************************/

    /*******************************************************************************
     *	Private Variables
     *******************************************************************************/
    private transient final HttpCookie cookie;
    private transient HttpCookie clientCookie;
    /*******************************************************************************
     *	Overrides From Base
     *******************************************************************************/

    /*******************************************************************************
     *	Public/Protected Methods
     *******************************************************************************/


    public SerializableHttpCookie(HttpCookie cookie) {
        this.cookie = cookie;
    }

    public HttpCookie getCookie() {
        HttpCookie bestCookie = cookie;
        if (clientCookie != null) {
            bestCookie = clientCookie;
        }
        return bestCookie;
    }

    /*******************************************************************************
     *	Private Methods
     *******************************************************************************/
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.writeObject(cookie.getName());
        out.writeObject(cookie.getValue());
        out.writeObject(cookie.getComment());
        out.writeObject(cookie.getCommentURL());
        out.writeObject(cookie.getDomain());
        out.writeLong(cookie.getMaxAge());
        out.writeObject(cookie.getPath());
        out.writeObject(cookie.getPortlist());
        out.writeInt(cookie.getVersion());
        out.writeBoolean(cookie.getSecure());
        out.writeBoolean(cookie.getDiscard());
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        String name = (String) in.readObject();
        String value = (String) in.readObject();
        clientCookie = new HttpCookie(name, value);
        clientCookie.setComment((String) in.readObject());
        clientCookie.setCommentURL((String) in.readObject());
        clientCookie.setDomain((String) in.readObject());
        clientCookie.setMaxAge(in.readLong());
        clientCookie.setPath((String) in.readObject());
        clientCookie.setPortlist((String) in.readObject());
        clientCookie.setVersion(in.readInt());
        clientCookie.setSecure(in.readBoolean());
        clientCookie.setDiscard(in.readBoolean());
    }
    /*******************************************************************************
     *	Internal Class,Interface
     *******************************************************************************/
}
