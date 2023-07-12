import Link from 'next/link';

import { Background } from '../background/Background';
import { Button } from '../button/Button';
import { HeroOneButton } from '../hero/HeroOneButton';
import { Section } from '../layout/Section';
import { NavbarTwoColumns } from '../navigation/NavbarTwoColumns';
import { Logo } from './Logo';

const Hero = () => (
  <Background color="whitesmoke">
    <Section yPadding="py-6">
      <NavbarTwoColumns logo={<Logo xl />}>
        <li>
        </li>
        <li>
          <Link href="/">
            <a>Sign in</a>
          </Link>
        </li>
      </NavbarTwoColumns>
    </Section>

    <Section yPadding="pt-20 pb-32">
      <HeroOneButton
        title={
          <>
            {"Unlock the potential of vouchers with\n"}
            <span className="text-primary-500">VNVoucher</span>
          </>
        }
        description="A cutting-edge Voucher Management System that empowers businesses to create, track, and deliver compelling vouchers, optimizing customer engagement and driving e-commerce success."
        button={
          <Link href="https://creativedesignsguru.com/category/nextjs/">
            <a>
              <Button xl>Get Started Now</Button>
            </a>
          </Link>
        }
      />
    </Section>
  </Background>
);

export { Hero };
