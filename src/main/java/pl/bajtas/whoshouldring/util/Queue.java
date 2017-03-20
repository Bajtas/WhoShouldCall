package pl.bajtas.whoshouldring.util;

import org.apache.commons.lang3.StringUtils;
import pl.bajtas.whoshouldring.persistence.model.User;
import pl.bajtas.whoshouldring.storage.queue.Category;
import pl.bajtas.whoshouldring.storage.queue.Chart;
import pl.bajtas.whoshouldring.storage.queue.ChartWrapper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Bajtas on 19.03.2017.
 */
public class Queue {
    public static void sortAsc(List<User> users) {
        users.sort((u1, u2) -> {
            if (u1.getLastCall().after(u2.getLastCall())) {
                return 1;
            } else if (u1.getLastCall().before(u2.getLastCall())) {
                return -1;
            }
            return 0;
        });
    }

    public static List<User> toList(Set<User> users) {
        List<User> listOfUsers = new ArrayList<>();
        listOfUsers.addAll(users);

        return listOfUsers;
    }

    public static ChartWrapper processList(List<User> users) {
        List<User> newOnes = getNewOnes(users);
        users.removeAll(newOnes);

        sortAsc(users);
        ChartWrapper chartWrapper = prepareChartWrapper(users);
        List<Category> categories = prepareMainCategories(users);
        addNewOneToMainCategory(newOnes, categories);
        addLevelsToMainCategory(categories.get(0), users);
        chartWrapper.setCategory(repackMainCategoryToArray(categories));

        return chartWrapper;
    }

    private static Category[] repackMainCategoryToArray(List<Category> categories) {
        return categories.toArray(new Category[categories.size()]);
    }

    private static void addLevelsToMainCategory(Category mainCategory, List<User> users) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for (User user : users) {
            String login = user.getLogin();
            String color = ColorHexRandomizer.random();
            String callDate = sdf.format(user.getLastCall());
            Category category = new Category(login, color, callDate);

            if (mainCategory.getCategory() == null) {
                mainCategory.setCategory(category);
            } else
                mainCategory.setLastCategory(category);
        }
    }

    private static void addNewOneToMainCategory(List<User> newOnes, List<Category> categories) {
        for (User chosenOnes : newOnes) {
            categories.add(new Category(chosenOnes.getLogin(), ColorHexRandomizer.random(), "Never"));
        }
    }

    private static List<User> getNewOnes(List<User> users) {
        List<User> newOnes = new ArrayList<>();
        for (User user : users) {
            if (user.getLastCall() == null)
                newOnes.add(user);
        }

        return newOnes;
    }

    private static ChartWrapper prepareChartWrapper(List<User> users) {
        ChartWrapper chartWrapper = new ChartWrapper();
        Chart chart = chartWrapper.getChart();
        chart.setCaption(users.get(0).getRingQueue().getName());
        chart.setSubcaption("Ordering peoples to call");

        return chartWrapper;
    }

    private static List<Category> prepareMainCategories(List<User> users) {
        User chosenOne = users.remove(0);
        List<Category> mainCategory = new ArrayList<>();
        mainCategory.add(new Category(chosenOne.getLogin(), ColorHexRandomizer.random(), new SimpleDateFormat("dd/MM/yyyy").format(chosenOne.getLastCall())));

        return mainCategory;
    }

    public static String getChosenOnes(Category[] categories) {
        String ret = StringUtils.EMPTY;
        for (int i = 0; i < categories.length; i++) {
            ret += categories[i].getLabel();
            if (categories.length > 1 && categories.length - 1 != i)
                ret += " | ";
        }
        return ret;
    }

    public static void sortAscEvenWithChosenOnes(List<User> usersForManagerForm) {
        usersForManagerForm.sort((u1, u2) -> {
            if (u1.getLastCall() == null)
                return -1;
            else if (u2.getLastCall() == null)
                return 1;
            if (u1.getLastCall().after(u2.getLastCall())) {
                return 1;
            } else if (u1.getLastCall().before(u2.getLastCall())) {
                return -1;
            }
            return 0;
        });
    }
}
