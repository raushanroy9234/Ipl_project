package com.mountBlue.iplProject;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.FileReader;
import java.util.*;
public class Main
{
    public static List<Match> findMatchesData()
    {
        List<Match> matchesData = new ArrayList<>();
        try
        {
        CSVParser parser = new CSVParser(new FileReader("/Users/makemymachine/IdeaProjects/ipl_project/src/main/java/org/mountblue/iplProject/matches.csv"), CSVFormat.DEFAULT.withHeader());
        for(CSVRecord record : parser)
        {
            Match match = new Match();
            match.setId(record.get("id"));
            match.setSeason(record.get("season"));
            match.setCity(record.get("city"));
            match.setDate(record.get("date"));
            match.setTeam1(record.get("team1"));
            match.setTeam2(record.get("team2"));
            match.setTossWinner(record.get("toss_winner"));
            match.setTossDecision(record.get("toss_decision"));
            match.setResult(record.get("result"));
            match.setDlApplied(record.get("dl_applied"));
            match.setWinner(record.get("winner"));
            match.setWinByRuns(record.get("win_by_runs"));
            match.setWinByWickets(record.get("win_by_wickets"));
            match.setPlayerOfMatch(record.get("player_of_match"));
            match.setVenue(record.get("venue"));
            match.setUmpire1(record.get("umpire1"));
            match.setUmpire1(record.get("umpire2"));
            match.setUmpire1(record.get("umpire3"));
            matchesData.add(match);
        }
     }catch (Exception e)
        {
            e.printStackTrace();
        }
        return matchesData;
    }
    public static List<Delivery> findDeliveriesData(){
        List<Delivery> deliveriesData = new ArrayList<>();
        try{
            CSVParser parser = new CSVParser(new FileReader("/Users/makemymachine/IdeaProjects/ipl_project/src/main/java/org/mountblue/iplProject/deliveries.csv"),CSVFormat.DEFAULT.withHeader());
             for(CSVRecord record : parser){
                 Delivery delivery = new Delivery();
                 delivery.setMatchId(record.get("match_id"));
                 delivery.setInning(record.get("inning"));
                 delivery.setBattingTeam(record.get("batting_team"));
                 delivery.setBowlingTeam(record.get("bowling_team"));
                 delivery.setOver(record.get("over"));
                 delivery.setBall(record.get("ball"));
                 delivery.setBatsman(record.get("batsman"));
                 delivery.setNonStriker(record.get("non_striker"));
                 delivery.setBowler(record.get("bowler"));
                 delivery.setIsSuperOver(record.get("is_super_over"));
                 delivery.setWideRuns(record.get("wide_runs"));
                 delivery.setByeRuns(record.get("bye_runs"));
                 delivery.setLegByeRuns(record.get("legbye_runs"));
                 delivery.setNoBallRuns(record.get("noball_runs"));
                 delivery.setPenaltyRuns(record.get("penalty_runs"));
                 delivery.setBatsmanRuns(record.get("batsman_runs"));
                 delivery.setExtraRuns(record.get("extra_runs"));
                 delivery.setTotalRuns(record.get("total_runs"));
                 delivery.setPlayerDismissed(record.get("player_dismissed"));
                 delivery.setDismissalKind(record.get("dismissal_kind"));
                 delivery.setFielder(record.get("fielder"));
                 deliveriesData.add(delivery);
             }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return deliveriesData;
    }
    public static void numberOfMatchPlayedPerYearOverAllYear(List<Match> matches)
    {
        HashMap<String,Integer> matchesPlayedEverySeason = new HashMap<>();
        for(Match match : matches)
        {
            matchesPlayedEverySeason.put(match.getSeason(),matchesPlayedEverySeason.getOrDefault(match.getSeason(),0)+1);
        }
        System.out.println("Season"+"                       "+"Number of Player");
        for(Map.Entry<String,Integer> season :matchesPlayedEverySeason.entrySet())
        {
            System.out.println(season.getKey()+"______________________________"+season.getValue());
        }
    }
    public static void numberOfMatchWonAllTeamOverAllYear(List<Match> matches)
    {
        HashMap<String, Integer> matchesWonByEveryTeam = new HashMap<>();
        for (Match match : matches)
        {
            if (match.getWinner().equals(""))
            {
                continue;
            }
            matchesWonByEveryTeam.put(match.getWinner(), matchesWonByEveryTeam.getOrDefault(match.getWinner(), 0) + 1);
        }
        System.out.println("Name of Team"+"                            "+"Number of Matches");
        for (Map.Entry<String, Integer> teamName : matchesWonByEveryTeam.entrySet())
        {
            System.out.println(teamName.getKey() + "__________________________" + teamName.getValue());
        }
    }
    public static void extraRunConcededByEachTeam(List<Match> matchRecord, List<Delivery> deliveryRecord)
    {
        ArrayList<String> seasonId = new ArrayList<>();
        for (Match match : matchRecord)
        {
            if (match.getSeason().equals("2016"))
            {
                seasonId.add(match.getId());
            }
        }
        HashMap<String, Integer> runGivenByBowler = new HashMap<>();
        for (String sid : seasonId)
        {
            for (Delivery delivery : deliveryRecord)
            {
                if (sid.equals(delivery.getMatchId()))
                {
                    runGivenByBowler.put(delivery.getBowlingTeam(), runGivenByBowler.getOrDefault(delivery.getBowlingTeam(), 0) + Integer.parseInt(delivery.getExtraRuns()));
                }
            }
        }
        System.out.println("Team Name"+"                           "+"Extra Runs");
        for (String key : runGivenByBowler.keySet())
        {
            System.out.println(key + "________________________________" + runGivenByBowler.get(key));
        }
    }
    public static void topEconomicBowler(List<Match> matchRecord, List<Delivery> deliveryRecord)
    {
        ArrayList<String> seasonId = new ArrayList<>();
        for (Match mm : matchRecord)
        {
            if (mm.getSeason().equals("2015"))
            {
                seasonId.add(mm.getId());
            }
        }
        HashMap<String, Integer> bowlerGivenRun = new HashMap<>();
        HashMap<String, Integer> bowlerGivenBall = new HashMap<>();
        for (String sid : seasonId)
        {
            for (Delivery delivery : deliveryRecord)
            {
                if (sid.equals(delivery.getMatchId())) {
                    bowlerGivenRun.put(delivery.getBowler(),
                            bowlerGivenRun.getOrDefault(delivery.getBowler(), 0) + Integer.parseInt(delivery.getTotalRuns()));
                }
                if (sid.equals(delivery.getMatchId()))
                {
                    if (delivery.getNoBallRuns().equals("0") && delivery.getNoBallRuns().equals("0"))
                    {
                        bowlerGivenBall.put(delivery.getBowler(), bowlerGivenBall.getOrDefault(delivery.getBowler(), 0) + 1);
                    }
                }
            }
        }
        HashMap<String, Double> economyOfBowler = new HashMap<>();
        for (String key : bowlerGivenBall.keySet()) {
            economyOfBowler.put(key, bowlerGivenRun.get(key) / (double) (bowlerGivenBall.get(key) / 6));
        }

        ArrayList<Double> economyRate = new ArrayList<>();
        for (String key : economyOfBowler.keySet()) {
            economyRate.add(economyOfBowler.get(key));
        }
        Collections.sort(economyRate);
        System.out.println("Best Economic Bowler"+"____________________"+"Economy Rate");
        for (String key : economyOfBowler.keySet()) {
            if (economyOfBowler.get(key) == economyRate.get(0)) {
                System.out.println(key+"                                 "+economyRate.get(0));
            }
        }
    }
    public static void print_eleven_player_of_each_team(List<Match> matchRecord,List<Delivery> deliveryRecord)
    {
        HashMap<String, String> idAndNameOfEveryTeam = new HashMap<>();
        for (Match match : matchRecord) {
            if (match.getSeason().equals("2016"))
            {
                idAndNameOfEveryTeam.put(match.getId(), match.getTeam1());
            }
        }
        HashMap<String, HashSet<String>> playerOfEveryTeam = new HashMap<>();
        for (String key : idAndNameOfEveryTeam.keySet())
        {
            HashSet<String> batsmanAndBowlerOfEveryTeam = new HashSet<>();
            for (Delivery delivery : deliveryRecord)
            {
                if (key.equals(delivery.getMatchId()) && idAndNameOfEveryTeam.get(key).equals(delivery.getBattingTeam()))
                {
                    batsmanAndBowlerOfEveryTeam.add(delivery.getBowler());
                    batsmanAndBowlerOfEveryTeam.add(delivery.getBatsman());
                }
            }

            playerOfEveryTeam.put(idAndNameOfEveryTeam.get(key), batsmanAndBowlerOfEveryTeam);
        }
        for (String key : playerOfEveryTeam.keySet())
        {

            System.out.println("Name of Team -->"+key);
            HashSet<String> playerName = playerOfEveryTeam.get(key);
            int count = 0;
            for (String s : playerName)
            {
                System.out.println(s);
                count++;
                if (count == 11) break;
            }
        }
    }
    public static void main(String[] args)
    {

         List<Match> dataOfMatches = findMatchesData();
         List<Delivery> dataOfDeliveries = findDeliveriesData();

//          numberOfMatchPlayedPerYearOverAllYear(dataOfMatches);
//          numberOfMatchWonAllTeamOverAllYear(dataOfMatches);
        //   extraRunConcededByEachTeam(dataOfMatches,dataOfDeliveries);
//         print_eleven_player_of_each_team(dataOfMatches,dataOfDeliveries);
topEconomicBowler(dataOfMatches, dataOfDeliveries);
    }
}