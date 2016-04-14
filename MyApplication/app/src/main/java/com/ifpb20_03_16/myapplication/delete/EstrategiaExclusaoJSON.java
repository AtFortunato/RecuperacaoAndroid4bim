package com.ifpb20_03_16.myapplication.delete;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

/**
 * Created by Arthur on 13/04/2016.
 */
public class EstrategiaExclusaoJSON implements ExclusionStrategy {
    @Override
    public boolean shouldSkipField( FieldAttributes fa ) {
        // verifica se tem a anotação da exclusão do JSON
        return fa.getAnnotation( ExcluirJSON.class ) != null;
    }
    @Override
    public boolean shouldSkipClass( Class<?> type ) {
        // aceita qualquer classe
        return false;
    }
}
