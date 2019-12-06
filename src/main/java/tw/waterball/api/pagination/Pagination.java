/*
 * Copyright 2019 johnny850807
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package tw.waterball.api.pagination;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class Pagination<T> implements Iterable<Page<T>> {
    private Iterator<Page<T>> pageIterator;

    public Pagination(Iterator<Page<T>> pageIterator) {
        this.pageIterator = pageIterator;
    }

    public static <T> Pagination<T> fromList(List<T> list) {
        return new Pagination<>(new ListPagingIterator<>(list));
    }

    @Override
    public Iterator<Page<T>> iterator() {
        return pageIterator;
    }

    public List<T> aggregate() {
        List<T> items = new LinkedList<>();
        for (Page<T> page : this) {
            items.addAll(page.get());
        }
        return items;
    }

    public static <T> Pagination<T> empty() {
        return new Pagination<>(new Iterator<Page<T>>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public Page<T> next() {
                return null;
            }
        });
    }
}
