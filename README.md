# JSON-and-other-Javascript-stuff Calendar Portlet

This Google Calendar Portlet uses the Google Calendar API to display a calendar for the campus organizations, clubs, and other events

## Features

#### Calendar view

Calendar can be displayed either in week view or month view. Events will be color-coded by club or event type in the calendar. 

- This view can possibly be the maximized view (maximized.jsp)
- [ ] tbd
- [ ] tbd


#### Events view

Events are listed starting from the previous day to the next 6 or 7 days. The format is very similar to the Google Calendar widget in android:

- This view can possibly be the normal view (normal.jsp)
- [ ] tbd
- [ ] tbd

#### Customizability

Students can choose which student organizations and calendar feeds to subscribe to, and their calendar will populate with those events.

For example, I could subscribe to the UCI events calendar, the Red Cross Club calendar, and the ICSSC calendar. The events from those calendars will appear in my calendar in my Portal account, color-coded.

Ideally, for calendars that are restricted to those that are actually in the club / organization, the student's Portal account will authorize them to add the calendar. 


### Still need to add to this README

- [ ] Finish README

#### Java code example

```java
public static void main (String[] args)
{
	System.out.println("This is a test! I want a burrito!");
}
```

#### XML code example

```xml
<dependency>
    <groupId>com.calvin</groupId>
    <artifactId>test</artifactId>
    <version>42</version>
</dependency>
```

#### Maven code example

```
mvn package
```
