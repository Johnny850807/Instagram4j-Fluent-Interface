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

package tw.waterball.api.foreach;

import tw.waterball.api.InstagramComment;
import tw.waterball.api.InstagramFeed;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author johnny850807 (Waterball)
 */
public class ForeachFeed  {
    private List<? extends InstagramFeed> items;
    public ForeachFeed(List<? extends InstagramFeed> items) {
        this.items = items;
    }

    public ForeachFeed foreach(Consumer<? super InstagramFeed> consumer) {
        items.forEach(consumer);
        return this;
    }

    public ForeachFeed take(int num) {
        return new ForeachFeed(items.subList(0, Math.min(items.size(), num)));
    }

    public ForeachFeed filter(Predicate<InstagramFeed> predicate) {
        return new ForeachFeed(
                items.stream().filter(predicate).collect(Collectors.toList())
        );
    }
    public ForeachFeed like() {
        items.forEach(InstagramFeed::like);
        return this;
    }

    public ForeachFeed unlike() {
        items.forEach(InstagramFeed::unlike);
        return this;
    }

    public ForeachFeed comment(String message) {
        items.forEach(feed -> feed.comment(message));
        return this;
    }

    public List<InstagramComment> aggregateComments() {
        return items.stream()
                .flatMap(feed -> feed.getRecentComments().stream())
                .collect(Collectors.toList());
    }

    public ForeachUser aggregateCommenters() {
        return new ForeachUser(items.stream()
                .flatMap(feed -> feed.getRecentCommenters().stream())
                .collect(Collectors.toList()));
    }

    public List<? extends InstagramFeed> get() {
        return items;
    }
}
