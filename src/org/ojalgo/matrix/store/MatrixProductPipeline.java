/*
 * Copyright 1997-2016 Optimatika (www.optimatika.se)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.ojalgo.matrix.store;

import org.ojalgo.access.Access1D;

final class MatrixProductPipeline<N extends Number> extends MatrixPipeline<N> {

    private final Access1D<N> myLeft;
    private final MatrixStore<N> myRight;

    private MatrixProductPipeline(final MatrixSupplier<N> base) {

        super(base);

        myLeft = null;
        myRight = null;
    }

    MatrixProductPipeline(final Access1D<N> left, final MatrixStore<N> right) {

        super(right);

        myLeft = left;
        myRight = right;
    }

    public long countColumns() {
        return myRight.countColumns();
    }

    public long countRows() {
        return myLeft.count() / myRight.countRows();
    }

    @Override
    public void supplyTo(final ElementsConsumer<N> receiver) {
        receiver.fillByMultiplying(myLeft, myRight);
    }

}