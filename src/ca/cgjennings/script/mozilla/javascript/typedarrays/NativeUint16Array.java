/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package ca.cgjennings.script.mozilla.javascript.typedarrays;

import ca.cgjennings.script.mozilla.javascript.Context;
import ca.cgjennings.script.mozilla.javascript.IdFunctionObject;
import ca.cgjennings.script.mozilla.javascript.Scriptable;
import ca.cgjennings.script.mozilla.javascript.Undefined;

/**
 * An array view that stores 16-bit quantities and implements the JavaScript "Uint16Array" interface.
 * It also implements List<Integer> for direct manipulation in Java.
 */

public class NativeUint16Array
    extends NativeTypedArrayView<Integer>
{
    private static final long serialVersionUID = 7700018949434240321L;

    private static final String CLASS_NAME = "Uint16Array";
    private static final int BYTES_PER_ELEMENT = 2;

    public NativeUint16Array()
    {
    }

    public NativeUint16Array(NativeArrayBuffer ab, int off, int len)
    {
        super(ab, off, len, len * BYTES_PER_ELEMENT);
    }

    public NativeUint16Array(int len)
    {
        this(new NativeArrayBuffer(len * BYTES_PER_ELEMENT), 0, len);
    }

    @Override
    public String getClassName()
    {
        return CLASS_NAME;
    }

    public static void init(Context cx, Scriptable scope, boolean sealed)
    {
        NativeUint16Array a = new NativeUint16Array();
        a.exportAsJSClass(MAX_PROTOTYPE_ID, scope, sealed);
    }

    @Override
    protected NativeTypedArrayView construct(NativeArrayBuffer ab, int off, int len)
    {
        return new NativeUint16Array(ab, off, len);
    }

    @Override
    public int getBytesPerElement()
    {
        return BYTES_PER_ELEMENT;
    }

    @Override
    protected NativeTypedArrayView realThis(Scriptable thisObj, IdFunctionObject f)
    {
        if (!(thisObj instanceof NativeUint16Array)) {
            throw incompatibleCallError(f);
        }
        return (NativeUint16Array)thisObj;
    }

    @Override
    protected Object js_get(int index)
    {
        if (checkIndex(index)) {
            return Undefined.instance;
        }
        return ByteIo.readUint16(arrayBuffer.buffer, (index * BYTES_PER_ELEMENT) + offset, false);
    }

    @Override
    protected Object js_set(int index, Object c)
    {
        if (checkIndex(index)) {
            return Undefined.instance;
        }
        int val = Conversions.toUint16(c);
        ByteIo.writeUint16(arrayBuffer.buffer, (index * BYTES_PER_ELEMENT) + offset, val, false);
        return null;
    }

    @Override
    public Integer get(int i)
    {
        if (checkIndex(i)) {
            throw new IndexOutOfBoundsException();
        }
        return (Integer)js_get(i);
    }

    @Override
    public Integer set(int i, Integer aByte)
    {
        if (checkIndex(i)) {
            throw new IndexOutOfBoundsException();
        }
        return (Integer)js_set(i, aByte);
    }
}
