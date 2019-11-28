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

import tw.waterball.api.InstagramComment;
import tw.waterball.api.InstagramFeed;
import tw.waterball.api.InstagramUser;
import org.brunocvcunha.instagram4j.requests.*;
import org.brunocvcunha.instagram4j.requests.payload.InstagramFeedItem;
import org.brunocvcunha.instagram4j.requests.payload.InstagramGetMediaCommentsResult;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Instagram4JFeedAdapter extends AbstractInstagram4JFeedAdapter {
    private transient Instagram4JAdapter ig;

    Instagram4JFeedAdapter(Instagram4JAdapter ig, InstagramFeedItem feed) {
        super(feed);
        this.ig = ig;
    }

    @Override
    public InstagramFeed like() {
        ig.sendRequest(new InstagramLikeRequest(getPK()));
        return this;
    }

    @Override
    public InstagramFeed unlike() {
        ig.sendRequest(new InstagramUnlikeRequest(getPK()));
        return this;
    }

    @Override
    public InstagramFeed comment(String message) {
        ig.sendRequest(new InstagramPostCommentRequest(getPK(), message));
        return this;
    }

    @Override
    public List<InstagramComment> getRecentComments() {
        return AdapterWrapping.wrap4JComments(ig, ig.sendRequest(new InstagramGetMediaCommentsRequest(
                String.valueOf(getPK()), null)).getComments());
    }

    @Override
    public List<InstagramComment> getComments(int maxNum) {
        List<org.brunocvcunha.instagram4j.requests.payload.InstagramComment> items = TemplateUtils.requestForPagedItems(
                ig, maxNum,
                (nextMaxId) -> new InstagramGetMediaCommentsRequest(String.valueOf(getPK()), nextMaxId),
                InstagramGetMediaCommentsResult::getComments,
                InstagramGetMediaCommentsResult::isHas_more_comments,

                // the reason that we have to pass the pk of the first item of the page instead of result.getNext_max_id().
                // it's because that nextMaxId doesn't work for the comments api
                // https://github.com/brunocvcunha/instagram4j/issues/259
                (result, allItems) -> String.valueOf(allItems.get(0).getPk())
        );

        return AdapterWrapping.wrap4JComments(ig, items);
    }

    @Override
    public List<InstagramUser> getRecentCommenters() {
        return getRecentComments().stream()
                .map(InstagramComment::getCommenter)
                .collect(Collectors.toList());
    }

    @Override
    public List<InstagramUser> getCommenters(int maxNum) {
        return getComments(maxNum).stream()
                .map(InstagramComment::getCommenter)
                .collect(Collectors.toList());
    }

    @Override
    public List<InstagramUser> getDistinctCommenters(int maxNum) {
        // due to the api restriction, we cannot directly fetch the distinct commenter in the expected number
        // so we just get all commenters once then remain the distinct items.
        HashSet<Long> distinctPks = new HashSet<>();
        List<InstagramUser> commenters = getAllCommenters();
        ArrayList<InstagramUser> distinctCommenters = new ArrayList<>();
        for (InstagramUser commenter : commenters) {
            if (!distinctPks.contains(commenter.getPK())) {
                distinctPks.add(commenter.getPK());
                distinctCommenters.add(commenter);
            }
        }
        return distinctCommenters;
    }
}
