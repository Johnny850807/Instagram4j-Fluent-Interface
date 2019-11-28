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

package tw.waterball.impls.instagram4j;

import tw.waterball.api.Instagram;
import tw.waterball.api.InstagramFeed;
import tw.waterball.api.InstagramUser;
import org.brunocvcunha.instagram4j.requests.*;
import org.brunocvcunha.instagram4j.requests.payload.InstagramFeedItem;
import org.brunocvcunha.instagram4j.requests.payload.InstagramFeedResult;
import org.brunocvcunha.instagram4j.requests.payload.InstagramGetUserFollowersResult;
import org.brunocvcunha.instagram4j.requests.payload.InstagramUserSummary;
import tw.waterball.api.pagination.Pagination;
import tw.waterball.impls.instagram4j.pagination.InstagramFollowerPagingIterator;
import tw.waterball.impls.instagram4j.pagination.InstagramFollowingPagingIterator;

import java.util.Collections;
import java.util.List;

public class Instagram4JUserAdapter extends AbstractInstagram4jUserAdapter {

    Instagram4JUserAdapter(Instagram4JAdapter ig, org.brunocvcunha.instagram4j.requests.payload.InstagramUser user) {
        super(ig, user);
    }

    Instagram4JUserAdapter(Instagram4JAdapter ig, String username) {
        super(ig, username);
    }

    @Override
    public boolean hasFollowMe() {
        return getAllFollowings().aggregate().stream()
                .anyMatch(user -> user.getUsername().equals(ig.getUsername()));
    }

    @Override
    public InstagramUser follow() {
        ig.sendRequest(new InstagramFollowRequest(getPK()));
        return this;
    }

    @Override
    public InstagramUser unfollow() {
        ig.sendRequest(new InstagramUnfollowRequest(getPK()));
        return this;
    }

    @Override
    public InstagramUser likeRecentFeeds() {
        getRecentFeeds().forEach(InstagramFeed::like);
        return this;
    }

    @Override
    public Pagination<InstagramUser> getRecentFollowers() {
        return new Pagination<>(()-> new InstagramFollowerPagingIterator(ig, getPK()));
    }

    @Override
    public Pagination<InstagramUser> getRecentFollowings() {
        return new Pagination<>(()-> new InstagramFollowingPagingIterator(ig, getPK()));
    }

    @Override
    public Pagination<InstagramUser> getFollowers(int maxNum) {
        return new Pagination<>(()-> new InstagramFollowerPagingIterator(maxNum, ig, getPK()));
    }

    @Override
    public Pagination<InstagramUser> getFollowings(int maxNum) {
        return new Pagination<>(()-> new InstagramFollowingPagingIterator(maxNum, ig, getPK()));
    }

    @Override
    public List<InstagramFeed> getRecentFeeds() {
        List<InstagramFeedItem> feeds = ig.sendRequest(new InstagramUserFeedRequest(getPK())).getItems();
        return AdapterWrapping.wrap4JFeedItems(ig, feeds);
    }

    //TODO
    @Override
    public List<InstagramFeed> getFeeds(int maxNum) {
        List<InstagramFeedItem> items = TemplateUtils.requestForPagedItems(
                ig, maxNum,
                // InstagramUserFeedRequest constructor issue sees https://github.com/brunocvcunha/instagram4j/issues/368
                // it only works when the last two arguments are passed zero
                (nextMaxId) -> new InstagramUserFeedRequest(getPK(), nextMaxId, 0, 0),
                InstagramFeedResult::getItems,
                InstagramFeedResult::isMore_available,
                (result, allItems) -> result.getNext_max_id()
        );

        return AdapterWrapping.wrap4JFeedItems(ig, items);
    }

    @Override
    public InstagramUser sendDM(String message) {
        ig.sendRequest(InstagramDirectShareRequest.builder()
                .shareType(InstagramDirectShareRequest.ShareType.MESSAGE)
                .recipients(Collections.singletonList(String.valueOf(getPK())))
                .message(message).build());
        return this;
    }

}
