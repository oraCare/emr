/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.emr.constant;

import java.util.ArrayList;

/**
 *
 * @author Nishith Shah
 */
public class ApplicationConstant {
    public static final ArrayList<String> TYPE_PRIMARY_LIST = new ArrayList<String>();
    
    static {
        TYPE_PRIMARY_LIST.add("string");
        TYPE_PRIMARY_LIST.add("integer");
        TYPE_PRIMARY_LIST.add("long");
        TYPE_PRIMARY_LIST.add("boolean");
        TYPE_PRIMARY_LIST.add("float");
        TYPE_PRIMARY_LIST.add("byte");
        TYPE_PRIMARY_LIST.add("short");
        TYPE_PRIMARY_LIST.add("int");
        TYPE_PRIMARY_LIST.add("char");
    }
}