import ru.hse.scheduled.api.Framework;

module ru.hse.scheduled.client {
    requires ru.hse.scheduled.api;

    opens ru.hse.scheduled.client to ru.hse.scheduled.impl;

    uses Framework;
}