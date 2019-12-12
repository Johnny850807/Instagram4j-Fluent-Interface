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

/**
 * The paging iterator wrapper that helps limit the items number.
 * @author - johnny850807@gmail.com (Waterball)
 */
public abstract class PagingIterator<T> implements Iterator<Page<T>> {
    private boolean hasLimit;
    private int remainingCount;

    protected PagingIterator() {
        hasLimit = false;
    }

    protected PagingIterator(int remainingCount) {
        this.remainingCount = remainingCount;
        hasLimit = true;
    }

    @Override
    public boolean hasNext() {
        return (!hasLimit || remainingCount > 0) && hasNextPage();
    }

    @Override
    public Page<T> next() {
        Page<T> nextPage = getNextPage();
        if (hasLimit) {
            int pageSize = nextPage.size();
            nextPage.setItems(nextPage.get().subList(0 , Math.min(nextPage.size(), remainingCount))); // limit the page
            remainingCount -= pageSize;
        }
        return nextPage;
    }

    protected abstract boolean hasNextPage();

    protected abstract Page<T> getNextPage();
}
