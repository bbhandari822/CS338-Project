package com.binod.project.swing.domain;

import javax.swing.*;
import java.util.List;

/**
 * Created by Binod Bhandari on 9/5/18.
 */
public class ChannelCustomList <E> extends AbstractListModel<E> {

    private List<E> channelLists;

    public ChannelCustomList(List<E> list) {
        this.channelLists = list;
    }

    public void addElement(E element) {
        channelLists.add(element);
        int index = channelLists.size();
        //abstractList model method
        fireContentsChanged(element, index, index);
    }

    public int getSize() {
        return channelLists.size();
    }

    public E getElementAt(int index) {
        return channelLists.get(index);
    }
}
