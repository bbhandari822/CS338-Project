package com.binod.project.swing.domain;

/**
 * Created by Binod Bhandari on 9/5/18.
 */
public class ChannelBox {

    protected String channelName;

    public String getMemberName() {
        return channelName;
    }

    public void setMemberName(String channelName) {
        this.channelName = channelName;
    }

    public ChannelBox() {
    }

    public ChannelBox(String name) {
        setMemberName(name);
    }

    public String toString() {
        return this.channelName;
    }

    public int compareTo(Member another) {
        return this.channelName.compareTo(another.getMemberName());
    }

    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj instanceof Member) {
            Member another = (Member) obj;
            return this.channelName.equals(another.getMemberName());
        }
        return false;
    }

    public int hashCode() {
        return this.channelName.hashCode();
    }

}
