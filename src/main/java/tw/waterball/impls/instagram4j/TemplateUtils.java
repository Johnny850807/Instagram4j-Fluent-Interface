/*
 * Copyright 2019 Johnny850807
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

/*
 * Copyright 2019 Johnny850807
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package tw.waterball.impls.instagram4j;

import org.brunocvcunha.instagram4j.requests.InstagramGetRequest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public class TemplateUtils {

    /**
     * @param ig the Instagram4JAdapter instance
     * @param maxNum the max number of items request for
     * @param requestFactory factory creates the request given the 'nextMaxId' string
     * @param resultToItemsConverter converter converters the result to items given the current page result
     * @param isMoreAvailablePredicate predicate sees if the result shows there are more items available, it will request
     *                                 for the next page if true predicated
     * @param nextMaxIdSupplier supplier supplies the pk of each item, given the current result and the current resulted items
     * @param <T> request type
     * @param <R> result type
     * @param <I> item type
     * @return a list of requested items, if the item owner has the number of items greater than maxNum, maxNum of items will be
     * in the list, otherwise the number owner has of the items will be returned.
     */
    static <T extends InstagramGetRequest<R>, R, I> List<I> requestForPagedItems(Instagram4JAdapter ig, int maxNum,
                                                                                        Function<String, T> requestFactory,
                                                                                        Function<R, List<I>> resultToItemsConverter,
                                                                                        Predicate<R> isMoreAvailablePredicate,
                                                                                        BiFunction<R, List<I>, String> nextMaxIdSupplier) {
        int remainingNum = maxNum;
        ArrayList<I> items = new ArrayList<>();
        R result = ig.sendRequest(requestFactory.apply(null));  // first fetch can be done by sending a null max id
        boolean hasMoreAvailable;
        do {
            List<I> resultedItems = resultToItemsConverter.apply(result);
            items.addAll(resultedItems.subList(0, Math.min(remainingNum, resultedItems.size())));
            remainingNum -= resultedItems.size();
            hasMoreAvailable = isMoreAvailablePredicate.test(result);
            String nextMaxId = nextMaxIdSupplier.apply(result, resultedItems);
            if (hasMoreAvailable)
                result = ig.sendRequest(requestFactory.apply(nextMaxId));
        } while(remainingNum > 0 && hasMoreAvailable);

        return items;
    }

    /**
     * @param ig the Instagram4JAdapter instance
     * @param maxNum the max number of items request for
     * @param requestFactory factory creates the request given the 'nextMaxId' string
     * @param resultToItemsConverter converter converters the result to items given the current page result
     * @param isMoreAvailablePredicate predicate sees if the result shows there are more items available, it will request
     *                                 for the next page if true predicated
     * @param nextMaxIdSupplier supplier supplies the pk of each item, given the current result and the current resulted items
     * @param pkSupplier supplier supplies the pk of each item
     * @param <T> request type
     * @param <R> result type
     * @param <I> item type
     * @return a list of distinct requested items, if the item owner has the number of items greater than maxNum, maxNum of items will be
     * in the list, otherwise the number owner has of the items will be returned.
     */
    public static <T extends InstagramGetRequest<R>, R, I> List<I> requestForDistinctPagedItems(Instagram4JAdapter ig, int maxNum,
                                                                                                     Function<String, T> requestFactory,
                                                                                                     Function<R, List<I>> resultToItemsConverter,
                                                                                                     Predicate<R> isMoreAvailablePredicate,
                                                                                                    BiFunction<R, List<I>, String> nextMaxIdSupplier,
                                                                                                    Function<I, Object> pkSupplier) {
        ArrayList<I> items = new ArrayList<>();
        HashSet<Object> distinctPks = new HashSet<>();
        R result = ig.sendRequest(requestFactory.apply(null));  // first fetch can be done by sending a null max id

        boolean hasMoreAvailable;
        do {
            List<I> resultedItems = resultToItemsConverter.apply(result);

            // add as an item only when no duplicate pk item exists
            for (I item : resultedItems) {
                Object pk = pkSupplier.apply(item);
                if (!distinctPks.contains(pk))
                {
                    distinctPks.add(pk);
                    items.add(item);

                    if (items.size() == maxNum)
                        return items;
                }
            }

            hasMoreAvailable = isMoreAvailablePredicate.test(result);
            if (hasMoreAvailable)
                result = ig.sendRequest(requestFactory.apply(nextMaxIdSupplier.apply(result, items)));
        } while(hasMoreAvailable);

        return items;
    }
}
