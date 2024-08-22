package dev.wp.industrial_overdrive;

import net.swedz.tesseract.neoforge.registry.SortOrder;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.NonExtendable
public interface IOSortOrder {
    SortOrder GEAR = new SortOrder(0);
    SortOrder OTHER_GEAR = new SortOrder(1);
    SortOrder CASINGS = new SortOrder(2);
    SortOrder MACHINES = new SortOrder(3);
    SortOrder PARTS = new SortOrder(4);
    SortOrder RESOURCES = new SortOrder(5);
    SortOrder BUCKETS = new SortOrder(6);
}
