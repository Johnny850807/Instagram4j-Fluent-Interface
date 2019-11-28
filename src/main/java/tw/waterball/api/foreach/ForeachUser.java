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

import tw.waterball.api.InstagramUser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author johnny850807 (Waterball)
 */
public class ForeachUser {
    private List<? extends InstagramUser> items;

    public ForeachUser(List<? extends InstagramUser> items) {
        this.items = items;
    }

    public ForeachUser foreach(Consumer<? super InstagramUser> consumer) {
        items.forEach(consumer);
        return this;
    }

    public ForeachUser take(int num) {
        return new ForeachUser(items.subList(0, Math.min(items.size(), num)));
    }

    public ForeachUser filter(Predicate<InstagramUser> predicate) {
        return new ForeachUser(
                items.stream().filter(predicate).collect(Collectors.toList())
        );
    }

    public ForeachUser distinct() {
        HashSet<String> nameSet = new HashSet<>();
        List<InstagramUser> distinctUsers = new ArrayList<>();
        for (InstagramUser item : items) {
            if (!nameSet.contains(item.getUsername())) {
                distinctUsers.add(item);
                nameSet.add(item.getUsername());
            }
        }
        return new ForeachUser(distinctUsers);
    }

    public ForeachUser follow() {
        items.forEach(InstagramUser::follow);
        return this;
    }

    public ForeachUser unfollow() {
        items.forEach(InstagramUser::unfollow);
        return this;
    }

    public ForeachUser likeRecentFeeds() {
        items.forEach(InstagramUser::likeRecentFeeds);
        return this;
    }

    public ForeachUser aggregateRecentFollowers() {
        return new ForeachUser(items.stream()
                .flatMap(user -> user.getRecentFollowers().stream())
                .collect(Collectors.toList()));
    }

    public ForeachUser aggregateRecentFollowings() {
        return new ForeachUser(items.stream()
                .flatMap(user -> user.getRecentFollowings().stream())
                .collect(Collectors.toList()));
    }

    public ForeachUser aggregateFollowers(int num) {
        return new ForeachUser(items.stream()
                .flatMap(user -> user.getPagedFollowers(num).aggregate().stream())
                .collect(Collectors.toList()));
    }

    public ForeachUser aggregateFollowings(int num) {
        return new ForeachUser(items.stream()
                .flatMap(user -> user.getPagedFollowers(num).aggregate().stream())
                .collect(Collectors.toList()));
    }

    public ForeachFeed aggregateRecentFeeds() {
        return new ForeachFeed(items.stream()
                .flatMap(user -> user.getRecentFeeds().stream())
                .collect(Collectors.toList()));
    }

    public ForeachFeed getFeeds(int num) {
        return new ForeachFeed(items.stream()
                .flatMap(user -> user.getPagedFeeds(num).aggregate().stream())
                .collect(Collectors.toList()));
    }

    public ForeachUser sendDM(String message) {
        items.forEach(user -> user.sendDM(message));
        return this;
    }

    public List<? extends InstagramUser> get() {
        return items;
    }
}
