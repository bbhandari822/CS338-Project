package com.binod.project.swing.domain;

/**
 * Created by Binod Bhandari on 8/24/18.
 */

public class Member implements Comparable<Member> {

    protected String memberName;

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public Member() {
    }

    public Member(String name) {
        setMemberName(name);
    }

    public String toString() {
        return this.memberName;
    }

    public int compareTo(Member another) {
        return this.memberName.compareTo(another.getMemberName());
    }

    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj instanceof Member) {
            Member another = (Member) obj;
            return this.memberName.equals(another.getMemberName());
        }
        return false;
    }

    public int hashCode() {
        return this.memberName.hashCode();
    }
}
