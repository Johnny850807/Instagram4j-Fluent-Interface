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

package tw.waterball.impls.instagram4j.pagination;

import org.brunocvcunha.instagram4j.requests.InstagramGetMediaCommentsRequest;
import org.brunocvcunha.instagram4j.requests.InstagramUserFeedRequest;
import org.brunocvcunha.instagram4j.requests.payload.InstagramFeedResult;
import org.brunocvcunha.instagram4j.requests.payload.InstagramGetMediaCommentsResult;
import tw.waterball.api.InstagramComment;
import tw.waterball.api.InstagramFeed;
import tw.waterball.api.pagination.Page;
import tw.waterball.api.pagination.PagingIterator;
import tw.waterball.exceptions.InstagramException;
import tw.waterball.impls.instagram4j.AdapterWrapping;
import tw.waterball.impls.instagram4j.Instagram4JAdapter;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class InstagramCommentPagingIterator extends PagingIterator<InstagramComment> {
    private Instagram4JAdapter ig;
    private long userPk;
    private String nextMaxCommentPk;
    private InstagramGetMediaCommentsResult result;

    public InstagramCommentPagingIterator(Instagram4JAdapter ig, long userPk) {
        this.ig = ig;
        this.userPk = userPk;
    }

    public InstagramCommentPagingIterator(int limit, Instagram4JAdapter ig, long userPk) {
        super(limit);
        this.ig = ig;
        this.userPk = userPk;
    }

    @Override
    protected boolean hasNextPage() {
        return result == null /*first fetch*/ || result.isHas_more_comments();
    }

    @Override
    protected Page<InstagramComment> getNextPage() {
        this.result = ig.sendRequest(new InstagramGetMediaCommentsRequest(String.valueOf(userPk), nextMaxCommentPk));

        if (result.getComments() == null)
            throw new InstagramException("Get comments");
        // the reason that we have to pass the pk of the first item of the comment page instead of result.getNext_max_id().
        // is that the nextMaxId doesn't work for the comments api
        // https://github.com/brunocvcunha/instagram4j/issues/259
        nextMaxCommentPk = String.valueOf(result.getComments().get(0).getPk());

        return new Page<>(AdapterWrapping.wrap4JComments(ig, result.getComments()));
    }
}
