/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.memorygame;

/**
 *
 * @author jancsi
 */
public class NameBuilderImpl implements NameBuilder{
    private Name n;

    public NameBuilderImpl() {
        n = new Name();
    }
    
    @Override
    public NameBuilder setNamefield(String namefield) {
        n.setNamefield(namefield);
        return this;
    }

    @Override
    public Name build() {
        return n;
    }
    
}
