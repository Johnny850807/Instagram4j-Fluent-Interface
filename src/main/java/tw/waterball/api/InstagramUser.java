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

package tw.waterball.api;

import tw.waterball.api.foreach.ForeachFeed;
import tw.waterball.api.foreach.ForeachUser;
import tw.waterball.api.mocks.MockInstagram4JUserAdapter;
import tw.waterball.api.pagination.Pagination;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface InstagramUser extends InstagramPk {
    String getUsername();
    String getCity();
    String getCategory();
    boolean isPublic();
    default boolean isPrivate() {
        return !isPublic();
    }

    boolean hasFollowMe();
    default boolean hasNotFollowMe() {
        return !hasFollowMe();
    }

    InstagramUser follow();
    InstagramUser unfollow();
    InstagramUser likeRecentFeeds();

    List<InstagramUser> getRecentFollowers();
    List<InstagramUser> getRecentFollowings();
    Pagination<InstagramUser> getPagedFollowers(int maxNum);
    Pagination<InstagramUser> getPagedFollowings(int maxNum);

    default Pagination<InstagramUser> getPagedAllFollowers() {
        return getPagedFollowers(Integer.MAX_VALUE);
    }

    default Pagination<InstagramUser> getPagedAllFollowings() {
        return getPagedFollowings(Integer.MAX_VALUE);
    }

    List<InstagramFeed> getRecentFeeds();

    Pagination<InstagramFeed> getPagedFeeds(int num);

    default Pagination<InstagramFeed> getPagedAllFeeds() {
        return getPagedFeeds(Integer.MAX_VALUE);
    }

    InstagramUser sendDM(String message);

    default InstagramUser when(Predicate<InstagramUser> predicate) {
        if(predicate.test(this))
            return this;
        return new MockInstagram4JUserAdapter(getPK(), getUsername(), getCategory(), getCity());
    }

    default ForeachFeed foreachRecentFeed() {
        return new ForeachFeed(getRecentFeeds());
    }

    default Optional<InstagramFeed> getFirstFeedOptional() {
        List<InstagramFeed> feeds = getPagedFeeds(1).aggregate();
        return Optional.ofNullable(feeds.isEmpty() ? null : feeds.get(0));
    }

    default InstagramFeed getFirstFeed() {
        List<InstagramFeed> feeds = getPagedFeeds(1).aggregate();
        return feeds.get(0);
    }

    default ForeachUser foreachRecentFollowers() {
        return new ForeachUser(getRecentFollowers());
    }

    default ForeachUser foreachRecentFollowings() {
        return new ForeachUser(getRecentFollowings());
    }

    default ForeachUser foreachFollowers(int num) {
        return new ForeachUser(getPagedFollowers(num).aggregate());
    }

    default ForeachUser foreachFollowings(int num) {
        return new ForeachUser(getPagedFollowings(num).aggregate());
    }

    default ForeachUser foreachAllFollowers() {
        return new ForeachUser(getPagedAllFollowers().aggregate());
    }

    default ForeachUser foreachAllFollowings() {
        return new ForeachUser(getPagedAllFollowings().aggregate());
    }
}
