package net.time4j.calendar.hindu;

import net.time4j.engine.CalendarSystem;
import net.time4j.engine.EpochDays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


@RunWith(Parameterized.class)
public class AstroHinduLunarCalendarTest {

    // sample data taken from Dershovitz/Reingold for the Amanta-rule
    @Parameterized.Parameters(name= "{index}: extra/hindu-amanta({0}-{1}-{2}-{3}-{4})={5})")
    public static Iterable<Object[]> data() {
        return Arrays.asList(
            new Object[][] {
                {-529, 6, true, 11, false, -214193L},
                {-111, 9, false, 27, false, -61387L},
                {127, 8, false, 3, true, 25469L},
                {192, 8, false, 9, false, 49217L},
                {526, 10, false, 20, false, 171307L},
                {633, 3, false, 5, false, 210155L},
                {751, 8, false, 15, false, 253427L},
                {1070, 2, false, 6, false, 369740L},
                {1153, 2, false, 23, false, 400085L},
                {1247, 1, false, 8, false, 434355L},
                {1296, 12, false, 8, false, 452605L},
                {1345, 1, false, 23, false, 470160L},
                {1355, 2, false, 8, false, 473837L},
                {1448, 4, false, 1, false, 507850L},
                {1492, 11, false, 7, false, 524156L},
                {1549, 2, true, 4, false, 544676L},
                {1610, 7, false, 2, false, 567118L},
                {1616, 11, false, 29, false, 569477L},
                {1705, 3, false, 20, false, 601716L},
                {1737, 4, false, 5, false, 613424L},
                {1773, 5, false, 6, false, 626596L},
                {1825, 4, false, 5, false, 645554L},
                {1876, 5, false, 11, false, 664224L},
                {1896, 1, false, 13, false, 671401L},
                {1960, 1, false, 22, false, 694799L},
                {1986, 5, false, 20, false, 704424L},
                {1998, 7, false, 9, false, 708842L},
                {2000, 1, false, 14, false, 709409L},
                {2000, 7, false, 8, false, 709580L},
                {2048, 12, false, 14, false, 727274L},
                {2052, 12, false, 7, false, 728714L},
                {2095, 8, false, 14, false, 744313L},
                {2151, 4, false, 6, false, 764652L},
            }
        );
    }

    private static final HinduVariant ASTRO_AMANTA =
        HinduRule.AMANTA.variant().withModernAstronomy(0.0).with(HinduEra.KALI_YUGA).withElapsedYears();
    private static final CalendarSystem<HinduCalendar> CALSYS = ASTRO_AMANTA.getCalendarSystem();

    private HinduCalendar hindu;
    private long utcDays;

    public AstroHinduLunarCalendarTest(
        int year,
        int month,
        boolean leapMonth,
        int dom,
        boolean leapDay,
        long rataDie
    ) {
        super();

        int kyYear = HinduEra.KALI_YUGA.yearOfEra(HinduEra.VIKRAMA, year);
        HinduMonth m = HinduMonth.ofLunisolar(month);
        if (leapMonth) {
            m = m.withLeap();
        }
        HinduDay d = HinduDay.valueOf(dom);
        if (leapDay) {
            d = d.withLeap();
        }
        this.hindu = HinduCalendar.of(ASTRO_AMANTA, HinduEra.KALI_YUGA, kyYear, m, d);
        this.utcDays = EpochDays.UTC.transform(rataDie, EpochDays.RATA_DIE);
    }

    @Test
    public void fromHinduCalendar() {
        assertThat(
            CALSYS.transform(this.hindu),
            is(this.utcDays));
    }

    @Test
    public void toHinduCalendar() {
        assertThat(
            CALSYS.transform(this.utcDays),
            is(this.hindu));
    }

    @Test
    public void unixDays() {
        assertThat(
            this.hindu.get(EpochDays.UNIX),
            is(this.utcDays + 2 * 365));
    }

}
