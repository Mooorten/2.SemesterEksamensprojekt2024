MinKostplan.com er et eksamensprojekt til 2. Semester udarbejdet af Morten, Diaco, Seline og Søren. 
Programmet er udviklet til virksomheden MinKostplan. De søgte en hjemmeside, hvor brugere kan oprette sig, og ud fra deres personlige oplysninger, få beregnet deres BMR, som bliver brugt til at give dem skræddersyede opskrifter til deres individuelle behov og mål.
Programmet er uviklet i Java Springboot og bruger HTML som front-end. 
For at oprette sig som bruger skal programmet have følgende oplysninger:
    private Long userid;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String phone;
    private String weight;
    private String height;
    private int age;
    private String gender;
    private String goals;
    private String activitylevel;

    Når man først opretter sig som bruger får man automatisk tildelt rollen user. Hvis man skal være admin, skal det senere ændres i databasen. 
    private String role;

    Når programmet har alle brugerens oplysninger kan den ud fra vægt, højde, alder, køn, mål og akitivitetsniveau beregne brugerens BMR. 
    private double bmr;

    Brugerens BMR bliver udberegnet på følgende måde:
     public double calculateBMR(Long userId) {
        Optional<User> optionalUser = findUserByID(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            // Beregning af BMR baseret på Harris-Benedict formel
            double bmr;
            if ("Mand".equalsIgnoreCase(user.getGender())) {
                bmr = (10 * Double.parseDouble(user.getWeight())) + (6.25 * Double.parseDouble(user.getHeight())) - (5 * user.getAge()) + 5;
            } else if ("Kvinde".equalsIgnoreCase(user.getGender())) {
                bmr = (10 * Double.parseDouble(user.getWeight())) + (6.25 * Double.parseDouble(user.getHeight())) - (5 * user.getAge()) - 161;
            } else {
                throw new IllegalArgumentException("Ugyldig køn");
            }

            // Justering baseret på aktivitetsniveau
            double activityLevelMultiplier;
            switch (user.getActivitylevel()) {
                case "Stillesiddende":
                    activityLevelMultiplier = 1.2;
                    break;
                case "Let aktiv":
                    activityLevelMultiplier = 1.5;
                    break;
                case "Moderat aktiv":
                    activityLevelMultiplier = 1.7;
                    break;
                case "Meget aktiv":
                    activityLevelMultiplier = 1.9;
                    break;
                case "Atlet":
                    activityLevelMultiplier = 2.4;
                    break;
                default:
                    throw new IllegalArgumentException("Ugyldigt aktivitetsniveau");
            }

            // Justering baseret på brugerens mål
            double goalAdjustment;
            switch (user.getGoals()) {
                case "Tab dig":
                    goalAdjustment = -500;
                    break;
                case "Tag på":
                    goalAdjustment = 500;
                    break;
                case "Hold vægten":
                    goalAdjustment = 0;
                    break;
                case "Opbyg muskel":
                    goalAdjustment = 300;
                    break;
                default:
                    throw new IllegalArgumentException("Ugyldigt mål");
            }

            // Beregning af endelig BMR inklusive måljustering
            bmr = (bmr * activityLevelMultiplier) + goalAdjustment;
            return bmr;
        } else {
            throw new IllegalArgumentException("Brugeren med det angivne ID blev ikke fundet");
        }
    }
}
