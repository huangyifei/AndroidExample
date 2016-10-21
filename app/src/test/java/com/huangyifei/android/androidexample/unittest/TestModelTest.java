package com.huangyifei.android.androidexample.unittest;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by huangyifei on 16/10/18.
 */
public class TestModelTest {
    private TestModel mModel;

    @Before
    public void setUp() throws Exception {
        mModel = new TestModel();
    }

    @Test
    public void sum() throws Exception {
        assertEquals(6d, mModel.sum(1d, 5d), 0);
    }

}