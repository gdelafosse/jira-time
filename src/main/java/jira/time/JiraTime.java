package jira.time;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Hello world!
 */
public class JiraTime {
    private static final String REGEX = "(?:(?<weeks>\\d+)w)?(?:(?<days>\\d+)d)?(?:(?<hours>\\d+)h)?(?:(?<minutes>\\d+)m)?";

    private static final Pattern PATTERN = Pattern.compile(REGEX);

    int calculate(String expression) {
        Matcher matcher = PATTERN.matcher(expression);
        if (matcher.matches()) {
            int weeks = group(matcher, "weeks");
            int days = group(matcher, "days");
            int hours = group(matcher, "hours");
            int minutes = group(matcher, "minutes");

            return ((weeks*5+days)*8+hours)*60+minutes;
        } else {
            throw new IllegalArgumentException(String.format("%s is not a correct jira-time", expression));
        }
    }

    private int group(Matcher matcher, String groupName) {
        String value = matcher.group(groupName);
        return Integer.parseInt(value == null?"0":value);
    }
}
