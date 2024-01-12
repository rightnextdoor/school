package com.school.roster.payload;

import com.school.roster.model.SchoolBackgroundStudent;
import com.school.roster.model.User;

public class SchoolRequest {
    private User user;
    private SchoolBackgroundStudent schoolBackground;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public SchoolBackgroundStudent getSchoolBackground() {
        return schoolBackground;
    }

    public void setSchoolBackground(SchoolBackgroundStudent schoolBackground) {
        this.schoolBackground = schoolBackground;
    }
}
