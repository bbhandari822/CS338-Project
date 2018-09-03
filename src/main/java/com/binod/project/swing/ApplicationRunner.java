package com.binod.project.swing;

import com.binod.project.swing.chat.Server;
import com.binod.project.swing.user.LogIn;

import java.io.IOException;

/**
 * Created by Binod Bhandari on 7/4/18.
 */
public class ApplicationRunner {

    public static void main(String[] args) throws IOException {
        LogIn.main();
        new Server().connectToServer();

    }
}
