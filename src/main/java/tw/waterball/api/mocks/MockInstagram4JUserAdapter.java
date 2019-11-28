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

package tw.waterball.api.mocks;

import tw.waterball.api.InstagramFeed;
import tw.waterball.api.InstagramUser;
import tw.waterball.api.pagination.Pagination;
import tw.waterball.impls.instagram4j.AbstractInstagram4jUserAdapter;

import java.util.Collections;
import java.util.List;

/**
 * @author johnny850807 (Waterball)
 */
public class MockInstagram4JUserAdapter extends AbstractInstagram4jUserAdapter {

    public MockInstagram4JUserAdapter(long pk, String username, String category, String city) {
        super(pk, username, category, city);
    }

    @Override
    public boolean hasFollowMe() {
        return false;
    }

    @Override
    public InstagramUser follow() {
        return this;
    }

    @Override
    public InstagramUser unfollow() {
        return this;
    }

    @Override
    public InstagramUser likeRecentFeeds() {
        return this;
    }

    @Override
    public List<InstagramUser> getRecentFollowers() {
        return  Collections.emptyList();
    }

    @Override
    public List<InstagramUser> getRecentFollowings() {
        return  Collections.emptyList();
    }

    @Override
    public Pagination<InstagramUser> getPagedFollowers(int maxNum) {
        return  Pagination.empty();
    }

    @Override
    public Pagination<InstagramUser> getPagedFollowings(int maxNum) {
        return  Pagination.empty();
    }

    @Override
    public List<InstagramFeed> getRecentFeeds() {
        return Collections.emptyList();
    }

    @Override
    public Pagination<InstagramFeed> getPagedFeeds(int num) {
        return Pagination.empty();
    }

    @Override
    public InstagramUser sendDM(String message) {
        return this;
    }
}
