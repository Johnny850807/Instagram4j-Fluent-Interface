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

import tw.waterball.api.AbstractInstagramPk;
import tw.waterball.api.InstagramUser;
import org.brunocvcunha.instagram4j.requests.InstagramSearchUsernameRequest;

@SuppressWarnings("WeakerAccess")
public abstract class AbstractInstagram4jUserAdapter extends AbstractInstagramPk implements InstagramUser {
    protected transient Instagram4JAdapter ig;
    protected String username;
    protected boolean isPublic;
    private org.brunocvcunha.instagram4j.requests.payload.InstagramUser user;

    /**
     * User's info is Lazy-Loaded.
     */
    AbstractInstagram4jUserAdapter(Instagram4JAdapter ig, String username) {
        this.ig = ig;
        this.username = username;
    }

    AbstractInstagram4jUserAdapter(Instagram4JAdapter ig, org.brunocvcunha.instagram4j.requests.payload.InstagramUser user) {
        this.ig = ig;
        this.user = user;
        this.username = user.getUsername();
        this.isPublic = !user.is_private();
    }

    public AbstractInstagram4jUserAdapter(long pk, String username, String category, String city) {
        super(pk);
        this.user = new org.brunocvcunha.instagram4j.requests.payload.InstagramUser();
        this.username = username;
        user.setUsername(username);
        user.setCategory(category);
        user.setCity_name(city);
    }


    private void loadUserDataIfNotLoaded() {
        if (user == null)
            this.user = ig.sendRequest(new InstagramSearchUsernameRequest(username)).getUser();
    }

    @Override
    public long getPK() {
        loadUserDataIfNotLoaded();
        return user.getPk();
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getCity() {
        loadUserDataIfNotLoaded();
        return user.getCity_name();
    }

    @Override
    public String getCategory() {
        return user.getCategory();
    }

    @Override
    public boolean isPublic() {
        return isPublic;
    }
}