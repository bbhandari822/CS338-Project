package com.binod.project.swing.chat;

import java.io.OutputStream;
import java.net.Socket;
import java.util.Observable;

/**
 * Created by Binod Bhandari on 8/4/18.
 */
public class Client extends Observable{

    private Socket socket;
    private OutputStream outputStream;

}
