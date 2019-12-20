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

package tw.waterball.api;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class InstagramInboxThreadMessage {
    private String itemId;
    private String userId;
    private long timestamp;
    private Type type;
    private String content;
    private boolean like;

    public enum Type {
        text,
        placeholder /*audio, sticker or gif*/,
        media /*photo*/,
        action_log
    }

    public InstagramInboxThreadMessage(String itemId, String userId, long timestamp, Type type, String content, boolean like) {
        this.itemId = itemId;
        this.userId = userId;
        this.timestamp = timestamp;
        this.type = type;
        this.content = content;
        this.like = like;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public boolean isLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return content;
    }
}
