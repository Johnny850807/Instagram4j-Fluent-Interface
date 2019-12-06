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

import java.util.List;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class ListPagingIterator<T> extends PagingIterator<T> {
    @SuppressWarnings("FieldCanBeLocal")
    private int pageSize = 50;
    private List<T> items;
    private int left = 0;


    public ListPagingIterator(List<T> items) {
        this.items = items;
    }

    @Override
    protected boolean hasNextPage() {
        return left < items.size();
    }

    @Override
    protected Page<T> getNextPage() {
        Page<T> nextPage = new Page<>(items.subList(left, Math.min(left+pageSize, items.size())));
        left += pageSize;
        return nextPage;
    }
}
