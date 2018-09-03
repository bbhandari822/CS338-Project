package com.binod.project.swing.domain;

import javax.swing.*;
import java.util.List;

/**
 * Created by Binod Bhandari on 8/24/18.
 */
public class MemberCustomList<E> extends AbstractListModel<E> {
    private List<E> memberLists;

    public MemberCustomList(List<E> list) {
        this.memberLists = list;
    }

    public void addElement(E element) {
        memberLists.add(element);
        int index = memberLists.size();
        //abstractList model method
        fireContentsChanged(element, index, index);
    }

    public int getSize() {
        return memberLists.size();
    }

    public E getElementAt(int index) {
        return memberLists.get(index);
    }

}
