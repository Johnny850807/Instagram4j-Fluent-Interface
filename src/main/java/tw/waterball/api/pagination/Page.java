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
import java.util.List;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class Page<T> implements Iterable<T> {
    private List<T> items;

    public Page(List<T> items) {
        this.items = items;
    }

    public List<T> get() {
        return items;
    }

    public int size() {
        return items.size();
    }

    protected void setItems(List<T> items) {
        this.items = items;
    }

    @Override
    public Iterator<T> iterator() {
        return items.iterator();
    }
}
